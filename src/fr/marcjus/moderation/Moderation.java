package fr.marcjus.moderation;

import org.bukkit.plugin.java.JavaPlugin;

import fr.marcjus.moderation.command.CommandMod;

public class Moderation extends JavaPlugin {

	@Override
	public void onEnable() {
		System.out.println("Moderation MarcJus : on");
		getCommand("mod").setExecutor(new CommandMod());
	}
	
	@Override
	public void onDisable() {
		System.out.println("Moderation MarcJus : off");
	}

}
