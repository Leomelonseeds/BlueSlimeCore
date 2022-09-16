package com.github.sirblobman.api.menu;

import java.util.Optional;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

public interface IMenu extends InventoryHolder, Listener {
    /**
     * @return The plugin that owns this menu.
     */
    JavaPlugin getPlugin();

    /**
     * Use this method to open the menu.
     */
    void open();

    /**
     * Override this method to use a custom close action.
     *
     * @param e the InventoryCloseEvent of the action.
     */
    void onCustomClose(InventoryCloseEvent e);

    /**
     * Used when there is a button to open the previous menu,
     * or if closing the menu causes the previous one to open.
     *
     * @return An optional menu that caused this menu to open.
     */
    Optional<IMenu> getParentMenu();

    /**
     * Change the parent menu for this menu.
     *
     * @param parentMenu The new parent menu. Can be null.
     * @see #getParentMenu()
     */
    void setParentMenu(IMenu parentMenu);
}
