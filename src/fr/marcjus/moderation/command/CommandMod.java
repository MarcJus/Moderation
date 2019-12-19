package fr.marcjus.moderation.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import fr.marcjus.moderation.menu.CustomMenu;

public class CommandMod implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("moderation.mod")) {
				CustomMenu menu = new CustomMenu("Joueurs", 54);
				menu.createPlayersMenu();
				menu.openMenu(player);
			}else{
				player.sendMessage("§cVous n'avez pas le droit d'utiliser cette commande !");
			}
		} else {
			sender.sendMessage("§cVous devez etre un joueur pour executer cette commande !");
		}

		return false;
	}

}
