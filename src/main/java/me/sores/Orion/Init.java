package me.sores.Orion;

import me.sores.Orion.commands.OrionCommand;
import me.sores.Orion.handler.MenuHandler;
import me.sores.Orion.util.ItemDB;
import me.sores.Orion.util.abstr.AbstractInit;
import org.bukkit.plugin.Plugin;

/**
 * Created by sores on 12/31/2021.
 */
public class Init extends AbstractInit {

    private static Init instance;

    private ItemDB itemDatabase;

    public Init(Plugin plugin) {
        super(plugin);
        instance = this;

        initInstances();
        registerCommands();
        registerEvents();
    }

    @Override
    public void initInstances() {
        itemDatabase = new ItemDB(Orion.getInstance());

        initHandler(new MenuHandler(), true);
    }

    @Override
    public void registerEvents() {

    }

    @Override
    public void registerCommands() {
        registerCommand(getCommandRegistrar(), "orion", new OrionCommand());
    }

    @Override
    public void unload() {
        getHandlerList().forEach(handler -> {
            try{
                handler.unload();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }

    public ItemDB getItemDatabase() {
        return itemDatabase;
    }

    public static Init getInstance() {
        return instance;
    }

}
