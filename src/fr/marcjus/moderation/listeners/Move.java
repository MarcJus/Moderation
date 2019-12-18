package fr.marcjus.moderation.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move implements Listener {

	private Moderation main;

	public Move(Moderation main) {
		this.main = main;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (main.getManagers().get(player) != null) {
			if (main.getManagers().get(player).isFrezze()) {
				e.setCancelled(true);
				player.sendMessage("§cVous etes frezze !");
			}
		}

	}

}
