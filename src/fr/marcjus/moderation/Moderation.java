package fr.marcjus.moderation;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.marcjus.moderation.command.CommandMod;
import fr.marcjus.moderation.listeners.Jail;
import fr.marcjus.moderation.listeners.Connecting;
import fr.marcjus.moderation.listeners.Containment;
import fr.marcjus.moderation.listeners.Damages;
import fr.marcjus.moderation.listeners.InventoryListeners;
import fr.marcjus.moderation.listeners.Move;
import fr.marcjus.moderation.manager.PlayerManager;

public class Moderation extends JavaPlugin {
	
	private HashMap<String, PlayerManager> managers = new HashMap<>();
	private ArrayList<String> players = new ArrayList<>();

	@Override
	public void onEnable() {
		System.out.println("Moderation MarcJus : on");
		getCommand("mod").setExecutor(new CommandMod());
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Connecting(this), this);
		pm.registerEvents(new InventoryListeners(this), this);
		pm.registerEvents(new Move(this), this);
		pm.registerEvents(new Damages(this), this);
		pm.registerEvents(new Jail(), this);
		pm.registerEvents(new Containment(), this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("Moderation MarcJus : off");
	}

	public HashMap<String, PlayerManager> getManagers() {
		return managers;
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}

}
