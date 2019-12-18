package fr.marcjus.moderation.command;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.marcjus.moderation.listeners.Moderation;

public class Damages implements Listener {
	
	private Moderation main;

	public Damages(Moderation main) {
		this.main = main;
	}
	
	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent e){
		Entity ent = e.getEntity();
		Entity d = e.getDamager();
		if(d instanceof Player){
			Player player = (Player) d;
			if(main.getManagers().get(player).isOneshot()){
				if(ent instanceof Player){
					Player p = (Player) ent;
					if(main.getManagers().get(player).isGod()){
						e.setCancelled(true);
					}else{
						net.minecraft.server.v1_12_R1.Entity nmsent = ((CraftEntity) ent).getHandle();
						nmsent.killEntity();
					}
				}
				net.minecraft.server.v1_12_R1.Entity nmsent = ((CraftEntity) ent).getHandle();
				nmsent.killEntity();
			}
		}else if(d instanceof Projectile){
			Projectile p = (Projectile) ent;
			if(p.getShooter() instanceof Player){
				Player player = (Player) p.getShooter();
				if(main.getManagers().get(player).isOneshot()){
					net.minecraft.server.v1_12_R1.Entity nmsent = ((CraftEntity) ent).getHandle();
					nmsent.killEntity();
				}
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		Entity ent = e.getEntity();
		if(ent instanceof Player){
			Player player = (Player) ent;
			if(main.getManagers().get(player).isGod()){
				e.setCancelled(true);
			}
		}
	}

}
