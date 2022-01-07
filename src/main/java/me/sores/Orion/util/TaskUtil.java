package me.sores.Orion.util;

import me.sores.Orion.Orion;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by sores on 4/22/2021.
 */
public class TaskUtil {

    public static void runTask(Plugin plugin, Runnable runnable, boolean async){
        if(async){
            plugin.getServer().getScheduler().runTaskAsynchronously(Orion.getInstance(), runnable);
        }else{
            plugin.getServer().getScheduler().runTask(Orion.getInstance(), runnable);
        }
    }

    public static void runTaskLater(Plugin plugin, Runnable runnable, long delay, boolean async){
        if(async){
            plugin.getServer().getScheduler().runTaskLaterAsynchronously(Orion.getInstance(), runnable, delay);
        }else{
            plugin.getServer().getScheduler().runTaskLater(Orion.getInstance(), runnable, delay);
        }
    }

    public static void runTaskTimer(Plugin plugin, Runnable runnable, long delay, long timer, boolean async){
        if(async){
            plugin.getServer().getScheduler().runTaskTimerAsynchronously(Orion.getInstance(), runnable, delay, timer);
        }else{
            plugin.getServer().getScheduler().runTaskTimer(Orion.getInstance(), runnable, delay, timer);
        }
    }

    public static void runTaskTimer(Plugin plugin, BukkitRunnable runnable, long delay, long timer, boolean async){
        if(async){
            runnable.runTaskTimerAsynchronously(plugin, delay, timer);
        }else{
            runnable.runTaskTimer(plugin, delay, timer);
        }
    }

}
