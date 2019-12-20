package fr.marcjus.moderation.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.marcjus.moderation.Moderation;
import fr.marcjus.moderation.manager.PlayerManager;
import fr.marcjus.moderation.menu.CustomMenu;
import net.minecraft.server.v1_12_R1.EntityPlayer;

public class InventoryListeners implements Listener {

	private Moderation main;
	@SuppressWarnings("unused")
	private HashMap<String, PlayerManager> pm = null;

	public InventoryListeners(Moderation main) {
		this.main = main;
		if(main.getManagers() != null){
			pm = main.getManagers();
		}
	}

	@EventHandler
	public void onClickPlayersListMenu(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();
		Player player = (Player) e.getWhoClicked();
		if (inv != null) {
			if (inv.getName().equals("Joueurs")) {
					if (it.getType().equals(Material.SKULL_ITEM) && it.hasItemMeta()) {
						e.setCancelled(true);
						player.closeInventory();
						Player target = Bukkit.getPlayer(it.getItemMeta().getDisplayName());
						CustomMenu menu = new CustomMenu(target.getName(), 27);
						menu.createPlayerManagerMenu();
						menu.openMenu(player);
					}
			}

		}
	}

	@EventHandler
	public void onClickPlayerManager(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack it = e.getCurrentItem();
		Player player = (Player) e.getWhoClicked();
		if (inv != null ) {
			if (main.getPlayers().contains(inv.getName())) {
				String targetName = inv.getName();
				Player target = Bukkit.getPlayer(targetName);
				PlayerManager pm = new PlayerManager();
				if (main.getManagers().get(targetName) != null) {
					pm = main.getManagers().get(targetName);
				}
				switch (it.getType()) {

				case PACKED_ICE:
					if (!pm.isFrezze()) {
						pm.setFrezze(true);
						player.sendMessage("§aVous avez bien frezze §e" + target.getName() + "§a !");
						target.sendMessage("§cVous avez ete immobilise par §e" + player.getName() + "§c !");
					} else {
						pm.setFrezze(false);
						player.sendMessage("§aVous avez bien unfrezze §e" + target.getName() + "§a !");
						target.sendMessage("§cVous avez ete désimmobilise par §e" + player.getName() + "§c !");
					}
					break;

				case GLOWSTONE_DUST:
					if (!pm.isGlow()) {
						pm.setGlow(true);
						player.sendMessage("§e" + target.getName() + "§a brille !");
						target.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 2));
					} else {
						pm.setGlow(false);
						target.removePotionEffect(PotionEffectType.GLOWING);
						player.sendMessage("§e" + target.getName() + "§a ne brille plus !");
					}
					break;
				case CHEST:
					player.closeInventory();
					CustomMenu invsee = new CustomMenu("§eInventaire de " + target.getName(), 5 * 9);
					invsee.createInvSee(target);
					invsee.openMenu(player);
					break;
				case ENDER_CHEST:
					player.closeInventory();
					player.openInventory(target.getEnderChest());
					break;
				case ENDER_PEARL:
					player.sendMessage("§aTéléportation vers "+target.getName());
					player.closeInventory();
					player.teleport(target);
					break;
				case DIAMOND_CHESTPLATE:
					if(!pm.isGod()){
						pm.setGod(true);
						player.sendMessage(target.getName()+"§a est invincible !");
						target.sendMessage("§aVous etes invincible !");
					}else{
						pm.setGod(false);
						player.sendMessage(target.getName()+"§a n'est plus invincible !");
						target.sendMessage("§cVous n'etes plus invincible !");
					}
					break;
				case DIAMOND_SWORD:
					if(!pm.isOneshot()){
						pm.setOneshot(true);
						player.sendMessage("§e"+target.getName()+"§a oneshot les entiées !");
						target.sendMessage("§aTu one shot les entitées !");
					}else{
						pm.setOneshot(false);
						player.sendMessage("§e"+target.getName()+"§a ne oneshot plus les entiées !");
						target.sendMessage("§cTu ne one shot plus les entitées !");
					}
					break;
				case SKULL_ITEM:
					EntityPlayer pl = ((CraftPlayer) target).getHandle();
					pl.killEntity();
					player.sendMessage("§e"+target.getName()+"§c a ete tue");
					break;
				default:
					break;
				}
			}
		}
	}

}
