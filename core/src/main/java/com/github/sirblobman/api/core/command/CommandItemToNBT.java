package com.github.sirblobman.api.core.command;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.sirblobman.api.command.PlayerCommand;
import com.github.sirblobman.api.core.CorePlugin;
import com.github.sirblobman.api.nms.ItemHandler;
import com.github.sirblobman.api.nms.MultiVersionHandler;
import com.github.sirblobman.api.utility.ItemUtility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public final class CommandItemToNBT extends PlayerCommand {
    private final CorePlugin plugin;
    private final Gson prettyGson;

    public CommandItemToNBT(CorePlugin plugin) {
        super(plugin, "item-to-nbt");
        setPermissionName("blue.slime.core.command.item-to-nbt");
        this.plugin = plugin;

        // 1.8 doesn't have the setLenient() method in GsonBuilder.
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();

        try {
            gsonBuilder.setLenient();
        } catch (NoSuchMethodError error) {
            // 1.8 doesn't have the setLenient method in GsonBuilder.
        }

        this.prettyGson = gsonBuilder.create();
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        if (args.length == 1) {
            return Collections.singletonList("pretty");
        }

        return Collections.emptyList();
    }

    @Override
    public boolean execute(Player player, String[] args) {
        ItemStack item = getHeldItem(player);
        if (ItemUtility.isAir(item)) {
            sendMessage(player, "error.invalid-held-item");
            return true;
        }

        CorePlugin plugin = getCorePlugin();
        MultiVersionHandler multiVersionHandler = plugin.getMultiVersionHandler();
        ItemHandler itemHandler = multiVersionHandler.getItemHandler();
        String nbtString = itemHandler.toNBT(item);

        if (args.length > 0 && args[0].equalsIgnoreCase("pretty")) {
            nbtString = prettyJSON(player, nbtString);
        }

        String[] split = nbtString.split(Pattern.quote("\n"));
        player.sendMessage(split);
        return true;
    }

    private CorePlugin getCorePlugin() {
        return this.plugin;
    }

    private Gson getPrettyGson() {
        return this.prettyGson;
    }

    private String prettyJSON(Player player, String json) {
        try {
            Gson prettyGson = getPrettyGson();
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(json);
            return prettyGson.toJson(jsonElement);
        } catch (NoClassDefFoundError | Exception ex) {
            player.sendMessage("Could not parse into pretty JSON, sending normal...");
            return json;
        }
    }
}
