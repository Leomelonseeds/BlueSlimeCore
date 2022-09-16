package com.github.sirblobman.api.menu;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractPagedMenu extends AbstractMenu {
    private int currentPage;

    public AbstractPagedMenu(JavaPlugin plugin, Player player) {
        this(null, plugin, player);
    }

    public AbstractPagedMenu(IMenu parentMenu, JavaPlugin plugin, Player player) {
        super(parentMenu, plugin, player);
        this.currentPage = 1;
    }

    @Override
    public String getTitle() {
        String titleFormat = getTitleFormat();
        if (titleFormat == null) {
            return null;
        }

        int currentPage = getCurrentPage();
        String currentPageString = Integer.toString(currentPage);
        return titleFormat.replace("{page}", currentPageString);
    }

    public final int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int page) {
        this.currentPage = page;
    }

    public final void openNextPage() {
        int currentPage = getCurrentPage();
        int maximumPage = getMaxPages();

        int newPage = Math.min(currentPage + 1, maximumPage);
        if (currentPage == newPage) {
            return;
        }

        setCurrentPage(newPage);
        open();
    }

    public final void openPreviousPage() {
        int currentPage = getCurrentPage();
        int newPage = Math.max(currentPage - 1, 1);
        if (currentPage == newPage) {
            return;
        }

        setCurrentPage(newPage);
        open();
    }

    public abstract int getMaxPages();

    public abstract String getTitleFormat();
}
