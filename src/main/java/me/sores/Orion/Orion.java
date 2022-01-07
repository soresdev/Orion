package me.sores.Orion;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * Created by sores on 12/31/2021.
 */
public class Orion extends JavaPlugin {

    private static Orion instance;
    public static Random RAND = new Random();

    @Override
    public void onEnable() {
        instance = this;

        new Init(this);
        //message here
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Orion getInstance() {
        return instance;
    }

}
