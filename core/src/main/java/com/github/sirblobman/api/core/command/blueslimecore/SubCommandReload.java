package com.github.sirblobman.api.core.command.blueslimecore;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.sirblobman.api.command.Command;
import com.github.sirblobman.api.core.CorePlugin;

public final class SubCommandReload extends Command {

    public SubCommandReload(CorePlugin plugin) {
        super(plugin, "reload");
        setPermissionName("blue.slime.core.command.blueslimecore.reload");
    }

    @Override
    protected List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        JavaPlugin plugin = getPlugin();
        plugin.reloadConfig();

        sendMessage(sender, "command.blueslimecore.reload-success");
        return true;
    }
}
