package fr.marcjus.moderation.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.marcjus.moderation.Moderation;
import fr.marcjus.moderation.menu.CreateCustomMenu;

@SuppressWarnings("deprecation")
public class MenuInfoListener implements Listener {

	public boolean frezze = false;
	public boolean glowing = false;
	private boolean god;
	private Moderation main;

	public MenuInfoListener(Moderation main) {
		this.main = main;
	}

	@EventHandler
	public void onInteractInventory(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Player player = (Player) e.getWhoClicked();
		ItemStack it = e.getCurrentItem();
		Material type = it.getType();
		if (inv != null) {
			if (inv.getName().equals("§2Joueurs")) {
				if (it != null && it.getType().equals(Material.SKULL_ITEM)) {
					if (it.hasItemMeta()) {
						e.setCancelled(true);
						SkullMeta meta = (SkullMeta) it.getItemMeta();
						String owner = meta.getOwner();
						CreateCustomMenu menu = new CreateCustomMenu(27, owner);
						menu.createMenuPlayerManager();
						menu.openMenu(player);
					}

				}
			} else {

				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (it != null) {
						if (inv.getName().equals(pl.getName())) {
							if (type.equals(Material.PACKED_ICE)) {
								if (frezze) {
									player.sendMessage("§aLe joueur est unfrezze !");
									pl.sendMessage("§aVous pouvez bouger !");
									frezze = false;
								} else {
									player.sendMessage("§aLe joueur est frezze !");
									pl.sendMessage("§cVous ne pouvez plus bouger !");
									frezze = true;
								}
							} else if (type.equals(Material.GLOWSTONE_DUST)) {
								if (!glowing) {
									glowing = true;
									player.sendMessage("§aLe joueur est visible a travers les murs !");
									pl.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 3));
								} else {
									glowing = false;
									pl.removePotionEffect(PotionEffectType.GLOWING);
								}
							} else if (type.equals(Material.CHEST)) {
								player.closeInventory();
								player.openInventory(pl.getInventory());
							} else if (type.equals(Material.DIAMOND_CHESTPLATE)) {
								if (!god) {
									main.addGod(player);
								} else {
									main.removeGod(player);
								}
							} else if (type.equals(Material.ENDER_CHEST)) {
								player.closeInventory();
								player.openInventory(pl.getEnderChest());
							}else if(type.equals(Material.ENDER_PEARL)){
								player.sendMessage("Téléportation vers "+pl.getName());
								player.closeInventory();
								player.teleport(pl);
							}
						}
					}

				}

			}
		}

	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		e.setCancelled(frezze);
		if (frezze) {
			player.sendMessage("§cVous etes frezze ! ");
		}
	}

}
