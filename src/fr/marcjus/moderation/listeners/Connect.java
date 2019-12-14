package fr.marcjus.moderation.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.marcjus.moderation.Moderation;
import fr.marcjus.moderation.manager.PlayerManager;

public class Connect implements Listener{
	
	private Moderation main;
	
	public Connect(Moderation main){
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		Player player = e.getPlayer();
		if(!main.getPlayersManager().containsKey(player)){
			main.getPlayersManager().put(player, new PlayerManager(main, player));
		}
		
	}

}
