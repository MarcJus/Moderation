package fr.marcjus.moderation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.marcjus.moderation.manager.ModeratorManager;
import fr.marcjus.moderation.menu.CreateCustomMenu;

public class CommandMod implements CommandExecutor {

	private Moderation main;

	public CommandMod(Moderation main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("§4Vous devez etre un joueur pour executer cette commande ! ");
			return false;
		}

		if (cmd.getName().equalsIgnoreCase("mod")) {
			Player player = (Player) sender;
			if (!player.hasPermission("moderation.mod")) {
				player.sendMessage("§cVous n'avez pas la permission d'executer cette commande !");
				return false;
			}
			if (args.length == 0) {
				if (main.getModos().contains(player)) {
					main.removeModo(player);
					player.sendMessage("§cVous n'etes plus en mode moderateur !");
					ModeratorManager manager = main.getModosManager().get(player);
					manager.setOneShot(false);
					manager.setGod(false);
					player.setPlayerListName(player.getName());
					if (main.getModosManager().containsKey(player)) {
						main.getModosManager().remove(player);
					}
					return false;
				} else {
					ModeratorManager manager = new ModeratorManager(main, player);
					main.getModos().add(player);
					if (!main.getModosManager().containsKey(player)) {
						main.getPlayersManager().put(player, manager);
					}
					player.sendMessage("§2Vous etes maintenant en mode moderateur !");
					manager.setGod(true);
					manager.setOneShot(true);
					player.setPlayerListName("§a" + player.getName());
					return true;
				}

			} else if (args.length >= 1) {
				if (main.getModos().contains(player)) {
					ModeratorManager manager = main.getModosManager().get(player);
					if (args[0].equalsIgnoreCase("oneshot")) {
						if (args.length >= 2) {
							if (args[1].equalsIgnoreCase("on")) {
								manager.setOneShot(true);
							} else if (args[1].equalsIgnoreCase("off")) {
								manager.setOneShot(false);
							} else {
								player.sendMessage("§4Usage : /mod <oneshot|god> <on|off>");
							}
						}
						if (args.length == 1) {
							if (ModeratorManager.isOneShot()) {
								player.sendMessage("§4One shot actif");
							} else {
								player.sendMessage("One shot inactif");
							}
						}
					} else if (args[0].equalsIgnoreCase("god")) {
						if (args.length >= 2) {
							if (args[1].equalsIgnoreCase("on")) {
								manager.setGod(true);
							} else if (args[1].equalsIgnoreCase("off")) {
								manager.setGod(false);
							} else {
								player.sendMessage("§4Usage : /mod <oneshot|god> <on|off>");
							}

						} else if (args.length == 1) {
							if (manager.isGod()) {
								player.sendMessage("§aVous etes invulnerable !");
							} else {
								player.sendMessage("§cVous etes sensible aux dégats !");
							}
						}
					} else if (args[0].equalsIgnoreCase("info")) {
						CreateCustomMenu menu = new CreateCustomMenu(54, "§2Joueurs");
						menu.createMenuPlayerList();
						menu.openMenu(player);
					} else {
						player.sendMessage("§4Usage : /mod <oneshot|god> <on|off>");
					}
					return true;
				} else {
					player.sendMessage("§4Vous n'etes pas en mode modérateur !");
				}

			}

		}
		return false;
	}
}
