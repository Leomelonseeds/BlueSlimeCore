pipeline {
    agent any

    options {
        githubProjectProperty(projectUrlStr: "https://github.com/SirBlobman/BlueSlimeCore")
    }

    environment {
        DISCORD_URL = credentials('PUBLIC_DISCORD_WEBHOOK')
        MAVEN_DEPLOY = credentials('MAVEN_DEPLOY')
    }

    triggers {
        githubPush()
    }

    tools {
        jdk "JDK 17"
    }

    stages {
        stage("Gradle: Build (No Daemon)") {
            steps {
                withGradle {
                    sh("ls -alh /tmp")
                    sh("df -h /tmp")
                    sh("./gradlew clean build --refresh-dependencies --no-daemon --stacktrace")
                }
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'core/build/libs/BlueSlimeCore-*.jar, bungeecord/core/build/libs/BlueSlimeBungeeCore-*.jar', fingerprint: true
        }

        always {
            script {
                discordSend webhookURL: DISCORD_URL,
                        title: "${env.JOB_NAME}",
                        link: "${env.BUILD_URL}",
                        result: currentBuild.currentResult,
                        description: "**Build:** ${env.BUILD_NUMBER}\n**Status:** ${currentBuild.currentResult}",
                        enableArtifactsList: false,
                        showChangeset: true
            }
        }
    }
}
