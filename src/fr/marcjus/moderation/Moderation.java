package fr.marcjus.moderation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.marcjus.moderation.manager.EventManager;
import fr.marcjus.moderation.manager.ModeratorManager;
import fr.marcjus.moderation.manager.PlayerManager;

public class Moderation extends JavaPlugin {

	private ArrayList<Player> modos = new ArrayList<>();
	private List<String> modosName = new ArrayList<>();
	private HashMap<Player, PlayerManager> playersManager = new HashMap<>();
	private HashMap<Player, ModeratorManager> modosManager = new HashMap<>();

	@Override
	public void onEnable() {
		saveDefaultConfig();
		new EventManager(this).pluginManager();
		getCommand("mod").setExecutor(new CommandMod(this));
		for (Player player : Bukkit.getOnlinePlayers()){
			isModo(player);
		}
	}

	public boolean isModo(Player player) {
		if(modos.contains(player)){
			return true;
		}
		return false;
	}


	public void addModo(Player player) {
		if (!modos.contains(player) ) {
			modos.add(player);
		}
	}

	public void removeModo(Player player) {
		if(modos.contains(player) ){
			modos.remove(player);
		}
	}

	@Override
	public void onDisable() {

	}
	
	public ArrayList<Player> getModos(){
		return modos;
	}
	
	public List<String> getModosName(){
		return modosName;
	}
	
	public HashMap<Player, PlayerManager> getPlayersManager() {
		return playersManager;
	}


	public HashMap<Player, ModeratorManager> getModosManager() {
		return modosManager;
	}

}
