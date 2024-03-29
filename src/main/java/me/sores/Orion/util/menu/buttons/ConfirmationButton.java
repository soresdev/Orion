package me.sores.Orion.util.menu.buttons;

import me.sores.Orion.handler.MenuHandler;
import me.sores.Orion.util.TypeCallback;
import me.sores.Orion.util.menu.Button;
import me.sores.Orion.util.menu.Menu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by sores on 4/6/2021.
 */
public class ConfirmationButton extends Button {

    private boolean confirm;
    private TypeCallback<Boolean> callback;
    private boolean closeAfterResponse;

    public ConfirmationButton(boolean confirm, TypeCallback<Boolean> callback, boolean closeAfterResponse) {
        this.confirm = confirm;
        this.callback = callback;
        this.closeAfterResponse = closeAfterResponse;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.WOOL, 1, this.confirm ? ((byte) 5) : ((byte) 14));
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(this.confirm ? ChatColor.GREEN + "Confirm" : ChatColor.RED + "Cancel");
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if(this.confirm) player.playSound(player.getLocation(), Sound.LEVEL_UP, 2F, 0.90f);

        if (this.closeAfterResponse) {
            Menu menu = MenuHandler.getInstance().getOpenMenus().get(player.getUniqueId());

            if (menu != null) {
                menu.setClosedByMenu(true);
            }

            player.closeInventory();
        }

        this.callback.callback(this.confirm);
    }

}
