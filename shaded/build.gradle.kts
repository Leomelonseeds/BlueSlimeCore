import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.0"
}

group = "com.github.sirblobman.api"
version = "2.6-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // XSeries
    implementation("com.github.cryptomorin:XSeries:9.3.0")

    // Adventure
    implementation("net.kyori:adventure-platform-bukkit:4.3.0")
    implementation("net.kyori:adventure-platform-bungeecord:4.3.0")
    implementation("net.kyori:adventure-text-serializer-plain:4.13.0")
    implementation("net.kyori:adventure-text-minimessage:4.13.0")

    // bStats
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("org.bstats:bstats-bungeecord:3.0.1")
}

tasks {
    named<Jar>("jar") {
        enabled = false
    }

    named<ShadowJar>("shadowJar") {
        relocate("com.cryptomorin.xseries", "com.github.sirblobman.api.xseries")
        relocate("net.kyori", "com.github.sirblobman.api.adventure")
        relocate("org.bstats", "com.github.sirblobman.api.bstats")
    }

    build {
        dependsOn(shadowJar)
    }
}
