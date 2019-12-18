package fr.marcjus.moderation.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.marcjus.moderation.manager.PlayerManager;

public class Connecting implements Listener {
	
	private Moderation main;
	
	public Connecting(Moderation main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		main.getPlayers().add(player.getName());
		if(!main.getManagers().containsValue(player)){
			PlayerManager plm = new PlayerManager();
			main.getManagers().put(player, plm);
		}
	}

}
