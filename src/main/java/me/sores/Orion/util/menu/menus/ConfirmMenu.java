package me.sores.Orion.util.menu.menus;

import me.sores.Orion.util.TypeCallback;
import me.sores.Orion.util.menu.Button;
import me.sores.Orion.util.menu.Menu;
import me.sores.Orion.util.menu.buttons.ConfirmationButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sores on 4/6/2021.
 */
public class ConfirmMenu extends Menu {

    private String title;
    private TypeCallback<Boolean> response;
    private boolean closeAfterResponse;
    private Button centerButton;

    public ConfirmMenu(String title, TypeCallback<Boolean> response, boolean closeAfter, Button centerButton) {
        this.title = title;
        this.response = response;
        this.closeAfterResponse = closeAfter;
        this.centerButton = centerButton;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<>();

        for(int i = 0; i < 9; i++){
            buttons.put(i, getPlaceholderButton());
        }

        buttons.put(3, new ConfirmationButton(true, response, closeAfterResponse));
        buttons.put(5, new ConfirmationButton(false, response, closeAfterResponse));
        if(centerButton != null) buttons.put(4, centerButton);

        return buttons;
    }

    @Override
    public String getTitle(Player player) {
        return title;
    }

}
