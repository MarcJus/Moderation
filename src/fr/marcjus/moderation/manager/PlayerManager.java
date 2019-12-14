package fr.marcjus.moderation.manager;

import org.bukkit.entity.Player;

import fr.marcjus.moderation.Moderation;

public class PlayerManager {

	protected Moderation main;
	private Player player;
	protected boolean god = false;
	protected boolean frezze = false;
	protected boolean glowing = false;

	public PlayerManager(Moderation main, Player player) {
		this.main = main;
		this.player = player;
	}
	
	public void setGlowing(boolean glowing){
		this.glowing = glowing;
	}
	
	public boolean isGlowing(){
		return this.glowing;
	}
	
	public void setGod(boolean invulnerable) {
		
		if (invulnerable) {
			if (!this.god) {
				player.sendMessage("§aVous  etes maintenant invulnerable !");
			}

		} else {
			player.sendMessage("§cVous etes maintenant sensible aux dégats !");
		}
		this.god = invulnerable;
	}

	public boolean isGod() {
		return god;
	}

	
	
	public void setFrezze(boolean frezze){
		this.frezze = frezze;
	}
	
	public boolean isFrezze(){
		return frezze;
	}
	
}
