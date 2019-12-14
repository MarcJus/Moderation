package fr.marcjus.moderation.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.marcjus.moderation.Moderation;

public class ModeratorManager extends PlayerManager{
	
	private static boolean oneshot = false;
	private Player player;
	protected boolean invisible = false;

	public ModeratorManager(Moderation main, Player player) {
		super(main, player);
		this.player = player;
		super.setGod(true);
	}
	
	public void setOneShot(boolean oneshot) {

		ModeratorManager.oneshot = oneshot;
		if (oneshot) {
			player.sendMessage("§4One shot activé !");
		} else {
			player.sendMessage("§aOne shot désactivé !");
		}

	}
	
	@SuppressWarnings("deprecation")
	public void setInvisible(boolean invisible) {

		if (invisible) {
			if (this.invisible) {
				player.sendMessage("§2Vous etes invisible pour les joueurs normaux !");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (!pl.getUniqueId().equals(player.getUniqueId()) && !main.getModos().contains(pl)) {
						pl.hidePlayer(player);
						player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 2));
					}
				}
			}else{
				player.sendMessage("§2Vous etes de nouveaux visible par les autres joueurs !");
				for(Player pl : Bukkit.getOnlinePlayers()){
					pl.showPlayer(player);
				}
			}

		}
		this.invisible = invisible;

	}

	public boolean isInvisible() {
		return invisible;
	}

	public static boolean isOneShot() {
		return oneshot;
	}


}
