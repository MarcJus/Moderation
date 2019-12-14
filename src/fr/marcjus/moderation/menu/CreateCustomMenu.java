package fr.marcjus.moderation.menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.marcjus.moderation.MenuPlayerManagerItems;

@SuppressWarnings("deprecation")
public class CreateCustomMenu {

	private Inventory inv;
	public boolean frezze;
	public boolean glowing;

	public CreateCustomMenu(int size, String customName) {
		inv = Bukkit.createInventory(null, size, customName);
	}

	public void createMenuPlayerList() {

		for (Player pl : Bukkit.getOnlinePlayers()) {
			ItemStack it = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta meta = (SkullMeta) it.getItemMeta();
			meta.setOwner(pl.getName());
			meta.setDisplayName("§e" + pl.getName());
			meta.setLore(Arrays.asList("§aInfos de ", "§c" + pl.getName()));
			it.setItemMeta(meta);
			inv.addItem(it);

		}
	}

	public void createMenuPlayerManager() {

		for (MenuPlayerManagerItems it : MenuPlayerManagerItems.values()) {
			inv.addItem(it.getItem());
		}

	}

	public void createMenuInvSee(Player target) {

		for (int i = 0; i < 36; i++) {
			if(target.getInventory().getItem(i) != null){
				inv.setItem(i, target.getInventory().getItem(i));
			}
		}
		
		inv.setItem(36, target.getInventory().getHelmet());
		inv.setItem(37, target.getInventory().getChestplate());
		inv.setItem(38, target.getInventory().getLeggings());
		inv.setItem(39, target.getInventory().getBoots());

	}

	public void openMenu(Player player) {
		player.closeInventory();
		player.openInventory(inv);
	}

}
