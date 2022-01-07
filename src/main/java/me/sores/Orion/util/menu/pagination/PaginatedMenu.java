package me.sores.Orion.util.menu.pagination;

import me.sores.Orion.util.menu.Button;
import me.sores.Orion.util.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sores on 4/6/2021.
 */
public abstract class PaginatedMenu extends Menu {

    private int page = 1;

    {
        setUpdateAfterClick(false);
    }

    @Override
    public String getTitle(Player player) {
        return getPrePaginatedTitle(player);
    }

    /**
     * Changes the page number
     *
     * @param player player viewing the inventory
     * @param mod    delta to modify the page number by
     */
    public final void modPage(Player player, int mod) {
        page += mod;
        getButtons().clear();
        openMenu(player);
    }

    /**
     * @param player player viewing the inventory
     */
    public final int getPages(Player player) {
        int buttonAmount = getAllPagesButtons(player).size();

        if (buttonAmount == 0) {
            return 1;
        }

        return (int) Math.ceil(buttonAmount / (double) getMaxItemsPerPage(player));
    }

    @Override
    public final Map<Integer, Button> getButtons(Player player) {
        int minIndex = (int) ((double) (page - 1) * getMaxItemsPerPage(player));
        int maxIndex = (int) ((double) (page) * getMaxItemsPerPage(player));

        HashMap<Integer, Button> buttons = new HashMap<>();

        buttons.put(45, new PageButton(-1, this));
        buttons.put(53, new PageButton(1, this));

        for (int i = 46; i < 53; i++) {
            buttons.put(i, getPlaceholderButton());
        }

        for (Map.Entry<Integer, Button> entry : getAllPagesButtons(player).entrySet()) {
            int ind = entry.getKey();

            if (ind >= minIndex && ind < maxIndex) {
                ind -= (int) ((double) (getMaxItemsPerPage(player)) * (page - 1));
                buttons.put(ind, entry.getValue());
            }
        }

        Map<Integer, Button> global = getGlobalButtons(player);

        if (global != null) {
            for (Map.Entry<Integer, Button> gent : global.entrySet()) {
                buttons.put(gent.getKey(), gent.getValue());
            }
        }

        return buttons;
    }

    public int getMaxItemsPerPage(Player player) {
        return 45;
    }

    /**
     * @param player player viewing the inventory
     *
     * @return a Map of button that returns items which will be present on all pages
     */
    public Map<Integer, Button> getGlobalButtons(Player player) {
        return null;
    }

    /**
     * @param player player viewing the inventory
     *
     * @return title of the inventory before the page number is added
     */
    public abstract String getPrePaginatedTitle(Player player);

    /**
     * @param player player viewing the inventory
     *
     * @return a map of button that will be paginated and spread across pages
     */
    public abstract Map<Integer, Button> getAllPagesButtons(Player player);

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }
}
