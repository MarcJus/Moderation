package fr.marcjus.moderation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.marcjus.moderation.manager.EventManager;

public class Moderation extends JavaPlugin {

	private ArrayList<Player> modos = new ArrayList<>();
	private List<String> modosName = new ArrayList<>();
	private ArrayList<Player> god = new ArrayList<>();
	private ArrayList<Player> vanish = new ArrayList<>();

	@Override
	public void onEnable() {
		saveDefaultConfig();
		new EventManager(this).pluginManager();
		getCommand("mod").setExecutor(new CommandMod(this));
		for (Player player : Bukkit.getOnlinePlayers()){
			isModo(player);
		}
		god.clear();
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

	public ArrayList<Player> getGod() {
		return god;
	}
	
	public void addGod(Player player){
		god.add(player);
	}
	
	public void removeGod(Player player){
		god.remove(player);
	}
	
	public boolean isGod(Player player){
		return god.contains(player);
	}
	
	public boolean isVanish(Player player){
		return vanish.contains(player);
	}
	
	public ArrayList<Player> getVanish(){
		return vanish;
	}
	
	public void addVanish(Player player){
		vanish.add(player);
	}
	
	public void removeVanish(Player player){
		vanish.remove(player);
	}

}
