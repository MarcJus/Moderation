package fr.marcjus.moderation.manager;

import org.bukkit.entity.Player;

import fr.marcjus.moderation.Moderation;

public class ModeratorManager extends PlayerManager{
	
	private static boolean oneshot = false;
	private Player player;

	public ModeratorManager(Moderation main, Player player) {
		super(main, player);
		this.player = player;
	}
	
	public void setOneShot(boolean oneshot) {

		ModeratorManager.oneshot = oneshot;
		if (oneshot) {
			player.sendMessage("§4One shot activé !");
		} else {
			player.sendMessage("§aOne shot désactivé !");
		}

	}

	public static boolean isOneShot() {
		return oneshot;
	}


}
