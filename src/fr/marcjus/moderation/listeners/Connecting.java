package fr.marcjus.moderation.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.marcjus.moderation.Moderation;
import fr.marcjus.moderation.manager.PlayerManager;

public class Connecting implements Listener {
	
	private Moderation main;
	
	public Connecting(Moderation main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		if(!main.getPlayers().contains(player.getName())){
			main.getPlayers().add(player.getName());
		}
		if(!main.getManagers().containsKey(player)){
			PlayerManager plm = new PlayerManager();
			main.getManagers().put(player.getName(), plm);
		}
	}

}
