package fr.marcjus.moderation.listeners;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.marcjus.moderation.Moderation;

public class Connect implements Listener{
	
	private Moderation main;
	private Configuration config;
	
	public Connect(Moderation main){
		this.main = main;
		this.config = main.getConfig();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		Player player = e.getPlayer();
		
	}

}
