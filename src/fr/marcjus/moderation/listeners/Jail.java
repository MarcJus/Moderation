package fr.marcjus.moderation.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import fr.marcjus.moderation.manager.JailManager;

public class Jail implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player player = e.getPlayer();
		if(JailManager.isPrisonner(player)){
			e.setCancelled(true);
			player.sendMessage("§cVous ne pouvez pas casser de blocs en prison !");
		}
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e){
		Player player = e.getPlayer();
		if(JailManager.isPrisonner(player)){
			e.setCancelled(true);
			player.sendMessage("§cVous ne pouvez pas vous teleporter en prison !");
		}
	}

}
