package fr.marcjus.moderation.manager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.marcjus.moderation.Moderation;
import fr.marcjus.moderation.listeners.Damages;
import fr.marcjus.moderation.listeners.MenuInfoListener;

public class EventManager {
	
	private Moderation main;
	
	public EventManager(Moderation main) {
		this.main = main;
	}
	
	public void pluginManager(){
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Damages(main), main);
		pm.registerEvents(new MenuInfoListener(main), main);
		
	}

}
