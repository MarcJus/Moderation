package fr.marcjus.moderation.listeners;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.marcjus.moderation.Moderation;
import fr.marcjus.moderation.manager.ModeratorManager;

public class Damages implements Listener {
	
	private Moderation main;
	
	public Damages(Moderation main){
		this.main = main;
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		Entity ent = e.getEntity();
		Entity damager = e.getDamager();
		if(damager instanceof Player){
			if(ModeratorManager.isOneShot()){
				net.minecraft.server.v1_12_R1.Entity nms = ((CraftEntity) ent).getHandle();
				nms.killEntity();
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		Entity ent = e.getEntity();
		if(ent instanceof Player){
			Player player = (Player) ent;
			if(main.isGod(player)){
				e.setCancelled(true);
			}
		}
	}
	
}
