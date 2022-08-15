package me.sores.Orion.handler;

import com.google.common.collect.Maps;
import me.sores.Orion.Orion;
import me.sores.Orion.util.TaskUtil;
import me.sores.Orion.util.handler.Handler;
import me.sores.Orion.util.menu.Button;
import me.sores.Orion.util.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

/**
 * Created by sores on 8/15/2022.
 */
public class MenuHandler extends Handler {

    private static MenuHandler instance;
    private Map<UUID, Menu> openMenus;

    public MenuHandler() {
        instance = this;
    }

    @Override
    public void init() {
        openMenus = Maps.newHashMap();

        TaskUtil.runTaskTimer(Orion.getInstance(), this::update, 20L, 10L, true);
    }

    @Override
    public void unload() {
        openMenus.clear();
    }

    public void update(){
        for(Map.Entry<UUID, Menu> entry : openMenus.entrySet()){
            UUID id = entry.getKey();
            Menu menu = entry.getValue();

            if(id == null || menu == null){
                openMenus.remove(id, menu);
                continue;
            }

            final Player player = Bukkit.getPlayer(id);
            if(player == null) return;

            Inventory cached = cacheInventory(player, menu);
            if(menu.isAutoUpdate()){
                player.getOpenInventory().getTopInventory().setContents(cached.getContents());
            }
        }
    }

    private Inventory cacheInventory(Player player, Menu menu){
        Inventory inventory = Bukkit.createInventory(player, menu.size(menu.getButtons()), menu.getTitle(player));

        for(Map.Entry<Integer, Button> buttonEntry : menu.getButtons(player).entrySet()){
            menu.getButtons().put(buttonEntry.getKey(), buttonEntry.getValue());

            final ItemStack item = menu.createItemStack(player, buttonEntry.getValue());

            inventory.setItem(buttonEntry.getKey(), item);
        }

        if(menu.isPlaceholder()){
            final Button placeholder = Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 15);

            for(int i = 0; i < menu.size(menu.getButtons()); i++){
                if(menu.getButtons().get(i) == null){
                    menu.getButtons().put(i, placeholder);
                    inventory.setItem(i, placeholder.getButtonItem(player));
                }
            }
        }

        return inventory;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onButtonPress(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Menu openMenu = MenuHandler.getInstance().getOpenMenus().get(player.getUniqueId());

        if (openMenu != null) {
            if (event.getSlot() != event.getRawSlot()) {
                if ((event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(true);
                }

                return;
            }

            if (openMenu.getButtons().containsKey(event.getSlot())) {
                Button button = openMenu.getButtons().get(event.getSlot());
                boolean cancel = button.shouldCancel(player, event.getClick());

                if (!cancel && (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(true);

                    if (event.getCurrentItem() != null) {
                        player.getInventory().addItem(event.getCurrentItem());
                    }
                } else {
                    event.setCancelled(cancel);
                }

                button.clicked(player, event.getClick());
                button.clicked(player, event.getSlot(), event.getClick(), event.getHotbarButton());

                if (MenuHandler.getInstance().getOpenMenus().containsKey(player.getUniqueId())) {
                    Menu newMenu = MenuHandler.getInstance().getOpenMenus().get(player.getUniqueId());

                    if (newMenu == openMenu) {
                        boolean buttonUpdate = button.shouldUpdate(player, event.getClick());

                        if ((newMenu.isUpdateAfterClick() && buttonUpdate) || buttonUpdate) {
                            openMenu.setClosedByMenu(true);
                            newMenu.openMenu(player);
                        }
                    }
                } else if (button.shouldUpdate(player, event.getClick())) {
                    openMenu.setClosedByMenu(true);
                    openMenu.openMenu(player);
                }

                if (event.isCancelled()) {
                    Bukkit.getScheduler().runTaskLater(Orion.getInstance(), player::updateInventory, 1L);
                }
            } else {
                if (event.getCurrentItem() != null) {
                    event.setCancelled(true);
                }

                if ((event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Menu openMenu = MenuHandler.getInstance().getOpenMenus().get(player.getUniqueId());

        if (openMenu != null) {
            openMenu.onClose(player);

            MenuHandler.getInstance().getOpenMenus().remove(player.getUniqueId());
        }
    }

    public Map<UUID, Menu> getOpenMenus() {
        return openMenus;
    }

    public static MenuHandler getInstance() {
        return instance;
    }
}
