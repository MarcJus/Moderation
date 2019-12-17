package fr.marcjus.moderation.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.marcjus.moderation.menu.CustomMenu;

public class CommandMod implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player){
			Player player = (Player) sender;
			CustomMenu menu = new CustomMenu("§aJoueurs", 54);
			menu.createPlayersMenu();
			menu.openMenu(player);
		}
		
		return false;
	}

}
