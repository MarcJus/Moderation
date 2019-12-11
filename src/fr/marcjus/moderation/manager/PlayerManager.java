package fr.marcjus.moderation.manager;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.marcjus.moderation.Moderation;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class PlayerManager {

	private Moderation main;
	private Player player;
	protected boolean invisible = false;
	protected boolean god = false;
	protected boolean frezze = false;

	public PlayerManager(Moderation main, Player player) {
		this.main = main;
		this.player = player;
	}
	
	public void setGod(boolean invulnerable) {
		
		if (invulnerable) {
			if (!this.god) {
				main.addGod(player);
				player.sendMessage("§aVous  etes maintenant invulnerable !");
			}

		} else {
			player.sendMessage("§cVous etes maintenant sensible aux dégats !");
			main.removeGod(player);
		}
		this.god = invulnerable;
	}

	public boolean isGod() {
		return god;
	}

	@SuppressWarnings("deprecation")
	public void setInvisible(boolean invisible) {

		if (invisible) {
			if (this.invisible) {
				main.addVanish(player);
				player.sendMessage("§2Vous etes invisible pour les joueurs normaux !");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (!pl.getUniqueId().equals(player.getUniqueId()) && !main.getModos().contains(pl)) {
						pl.hidePlayer(player);
						EntityPlayer nmsplayer = ((CraftPlayer) player).getHandle();
						EntityPlayer nmspl = ((CraftPlayer) player).getHandle();
						PlayerConnection connection = nmspl.playerConnection;
						connection
								.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, nmsplayer));
					}
				}
			}

		}
		this.invisible = invisible;

	}

	public boolean isInvisible() {
		return invisible;
	}
	
	public void setFrezze(boolean frezze){
		this.frezze = frezze;
	}
	
	public boolean isFrezze(){
		return frezze;
	}
	
}
