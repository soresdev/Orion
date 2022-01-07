package me.sores.Orion.util.profile;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by sores on 1/6/2022.
 */
public abstract class PlayerProfile implements IPlayerProfile {

    private final UUID uuid;

    public PlayerProfile(UUID uuid) {
        this.uuid = uuid;
    }

    public abstract void saveData() throws IOException;
    public abstract void loadData();

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    @Override
    public String getName() {
        return Bukkit.getPlayer(uuid) != null ? Bukkit.getPlayer(uuid).getName() : Bukkit.getOfflinePlayer(uuid).getName();
    }

    @Override
    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(uuid);
    }

    @Override
    public UUID getID() {
        return uuid;
    }

}
