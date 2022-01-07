package me.sores.Orion.util.scoreboard;

import me.sores.Orion.util.scoreboard.events.AssembleBoardCreateEvent;
import me.sores.Orion.util.scoreboard.events.AssembleBoardDestroyEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;

public class AssembleListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		AssembleBoardCreateEvent createEvent = new AssembleBoardCreateEvent(event.getPlayer());

		Bukkit.getPluginManager().callEvent(createEvent);
		if (createEvent.isCancelled()) {
			return;
		}

		Assemble.getInstance().getBoards().put(event.getPlayer().getUniqueId(), new AssembleBoard(event.getPlayer()));
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		AssembleBoardDestroyEvent destroyEvent = new AssembleBoardDestroyEvent(event.getPlayer());

		Bukkit.getPluginManager().callEvent(destroyEvent);
		if (destroyEvent.isCancelled()) {
			return;
		}

		Assemble.getInstance().getBoards().remove(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onPluginDisable(PluginDisableEvent event) {
		if (Assemble.getInstance().getThread() == null) {
			return;
		}

		Assemble.getInstance().getThread().stop();
	}

}
