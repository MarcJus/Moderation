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

import fr.marcjus.moderation.Moderation;
import fr.marcjus.moderation.manager.PlayerManager;
import fr.marcjus.moderation.menu.CreateCustomMenu;

@SuppressWarnings("deprecation")
public class MenuInfoListener implements Listener {

	private Moderation main;

	public MenuInfoListener(Moderation main) {
		this.main = main;
	}

	@EventHandler
	public void onInteractInventory(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Player player = (Player) e.getWhoClicked();
		ItemStack it = e.getCurrentItem();
		String ownerName = "";
		PlayerManager pm = main.getPlayersManager().get(player);

		if (inv != null) {
			if (inv.getName().equals("§2Joueurs")) {
				if (it != null && it.getType().equals(Material.SKULL_ITEM)) {
					if (it.hasItemMeta()) {
						e.setCancelled(true);
						SkullMeta meta = (SkullMeta) it.getItemMeta();
						ownerName = meta.getOwner();
						CreateCustomMenu menu = new CreateCustomMenu(27, ownerName);
						menu.createMenuPlayerManager();
						menu.openMenu(player);
					}

				}
			} else {

				Player pl = Bukkit.getPlayer(ownerName);
				if (it != null) {
					Material type = it.getType();
					if (inv.getName().equals(pl.getName())) {
						if (type.equals(Material.PACKED_ICE)) {
							if (pm.isFrezze()) {
								player.sendMessage("§aLe joueur est unfrezze !");
								pl.sendMessage("§aVous pouvez bouger !");
								pm.setFrezze(false);
							} else {
								player.sendMessage("§aLe joueur est frezze !");
								pl.sendMessage("§cVous ne pouvez plus bouger !");
								pm.setFrezze(true);
							}
						} else if (type.equals(Material.GLOWSTONE_DUST)) {
							if (!pm.isGlowing()) {
								pm.setGlowing(true);
								player.sendMessage("§aLe joueur est visible a travers les murs !");
								pl.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 3));
							} else {
								pm.setGlowing(false);
								player.sendMessage("§cLe joueur n'est plus visible a travers les murs !");
								pl.removePotionEffect(PotionEffectType.GLOWING);
							}
						} else if (type.equals(Material.CHEST)) {
							player.closeInventory();
							CreateCustomMenu invsee = new CreateCustomMenu(5 * 9, "§Inventaire de"+ownerName);
							invsee.createMenuInvSee(pl);
							invsee.openMenu(player);
						} else if (type.equals(Material.DIAMOND_CHESTPLATE)) {
							if (!pm.isGod()) {
								pm.setGod(true);
								player.sendMessage("§aLe joueur est invincible !");
							} else {
								pm.setGod(false);
								player.sendMessage("§cLe joueur n'est plus invincible !");
							}
						} else if (type.equals(Material.ENDER_CHEST)) {
							player.closeInventory();
							player.openInventory(pl.getEnderChest());
						} else if (type.equals(Material.ENDER_PEARL)) {
							player.sendMessage("Téléportation vers " + pl.getName());
							player.closeInventory();
							player.teleport(pl);
						}

					}

				}

			}
		}

	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		PlayerManager pm = main.getPlayersManager().get(player);
		if (pm.isFrezze()) {
			e.setCancelled(true);
			player.sendMessage("§cVous etes frezze ! ");
		}
	}

}
