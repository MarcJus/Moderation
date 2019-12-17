package fr.marcjus.moderation.menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CustomMenu {
	
	private Inventory inv;
	
	public CustomMenu(String name, int size) {
		Inventory inv = Bukkit.createInventory(null, size, name);
		this.inv = inv;
	}
	
	@SuppressWarnings("deprecation")
	public void createPlayersMenu(){
		for(Player player : Bukkit.getOnlinePlayers()){
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 3);
			SkullMeta meta = (SkullMeta) skull.getItemMeta();
			meta.setOwner(player.getName());
			meta.setDisplayName("§e"+player.getName());
			meta.setLore(Arrays.asList("§3Gerer le joueur", "§e"+player.getName()));
			skull.setItemMeta(meta);
			inv.addItem(skull);
		}
	}
	
	public void openMenu(Player player){
		player.openInventory(inv);
	}
	
}
