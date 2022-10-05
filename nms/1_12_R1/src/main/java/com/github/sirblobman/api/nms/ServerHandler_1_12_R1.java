package com.github.sirblobman.api.nms;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.PropertyManager;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;

public final class ServerHandler_1_12_R1 extends ServerHandler {
    public ServerHandler_1_12_R1(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public int getMaxWorldSize() {
        Server server = Bukkit.getServer();
        if (!(server instanceof CraftServer)) {
            return 29_999_984;
        }

        CraftServer craftServer = (CraftServer) server;
        MinecraftServer minecraftServer = craftServer.getServer();
        PropertyManager propertyManager = minecraftServer.getPropertyManager();
        return propertyManager.getInt("max-world-size", 29_999_984);
    }
}