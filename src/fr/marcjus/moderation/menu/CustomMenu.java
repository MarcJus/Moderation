package fr.marcjus.moderation.menu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CustomMenu {

	private Inventory inv;

	public CustomMenu(String name, int size) {
		Inventory inv = Bukkit.createInventory(null, size, name);
		this.inv = inv;
	}

	@SuppressWarnings("deprecation")
	public void createPlayersMenu() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
			SkullMeta meta = (SkullMeta) skull.getItemMeta();
			meta.setOwner(player.getName());
			meta.setDisplayName("§e"+player.getName());
			meta.setLore(Arrays.asList("§3Gerer le joueur"));
			skull.setItemMeta(meta);
			inv.addItem(skull);
		}
	}

	public void createPlayerManagerMenu() {
		ItemStack frezze = new ItemStack(Material.PACKED_ICE);
		ItemMeta frmeta = frezze.getItemMeta();
		frmeta.setDisplayName("§3Immobilise le joueur");
		frezze.setItemMeta(frmeta);

		ItemStack glow = new ItemStack(Material.GLOWSTONE_DUST);
		ItemMeta gmeta = glow.getItemMeta();
		gmeta.setDisplayName("§eFait briller le joueur");
		glow.setItemMeta(gmeta);

		ItemStack invsee = new ItemStack(Material.CHEST);
		ItemMeta invmeta = frezze.getItemMeta();
		invmeta.setDisplayName("§6Montre l'inventaire du joueur");
		invsee.setItemMeta(invmeta);

		ItemStack endersee = new ItemStack(Material.ENDER_CHEST);
		ItemMeta endmeta = endersee.getItemMeta();
		endmeta.setDisplayName("§2Montre l'enderchest du joueur");
		endersee.setItemMeta(endmeta);

		ItemStack tp = new ItemStack(Material.ENDER_PEARL);
		ItemMeta tm = tp.getItemMeta();
		tm.setDisplayName("§fSe teleporter au joueur");
		tp.setItemMeta(tm);

		ItemStack god = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta godmeta = god.getItemMeta();
		godmeta.setDisplayName("§cRend le joueur invincible");
		god.setItemMeta(godmeta);
		
		ItemStack oneshot = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta onemeta = oneshot.getItemMeta();
		onemeta.setDisplayName("§4Permet au joueur de one shot les entitées");
		onemeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
		onemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		oneshot.setItemMeta(onemeta);
		
		ItemStack kill = new ItemStack(Material.SKULL_ITEM);
		ItemMeta kmeta = kill.getItemMeta();
		kmeta.setDisplayName("§8Tue le joueur");
		kill.setItemMeta(kmeta);
		
		ItemStack speed = new ItemStack(Material.RABBIT_FOOT);
		ItemMeta smeta = speed.getItemMeta();
		smeta.setDisplayName("§dDonne un effet de vitesse");
		speed.setItemMeta(smeta);
		
		ItemStack fly = new ItemStack(Material.FEATHER);
		ItemMeta fmeta = fly.getItemMeta();
		fmeta.setDisplayName("§7Permet au joueur de voler");
		fly.setItemMeta(fmeta);
		
		ItemStack totem = new ItemStack(Material.TOTEM);
		ItemMeta tmeta = totem.getItemMeta();
		tmeta.setDisplayName("§9Espionner le joueur");
		totem.setItemMeta(tmeta);
		
		ItemStack feed = new ItemStack(Material.COOKED_CHICKEN);
		ItemMeta feedmeta = feed.getItemMeta();
		feedmeta.setDisplayName("§5Nourrit le joueur");
		feed.setItemMeta(feedmeta);
		
		ItemStack health = new ItemStack(Material.GOLDEN_APPLE, 1, (byte)1);
		ItemMeta hmeta = health.getItemMeta();
		hmeta.setDisplayName("§eSoigne le joueur");
		health.setItemMeta(hmeta);
		
		ItemStack jail = new ItemStack(Material.BARRIER);
		ItemMeta jmeta = jail.getItemMeta();
		jmeta.setDisplayName("§4Envoie de joueur en prison");
		jail.setItemMeta(jmeta);
		
		inv.addItem(frezze, glow, invsee, endersee, tp, god, oneshot, kill, speed, fly, totem, feed, health, jail);
	}
	
	@SuppressWarnings("deprecation")
	public void createMenuJail(Player target){
		ItemStack normal = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)13);
		ItemMeta nmeta = normal.getItemMeta();
		nmeta.setDisplayName("§aCelulle normale");
		normal.setItemMeta(nmeta);
		
		ItemStack cont = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
		ItemMeta cmeta = cont.getItemMeta();
		cmeta.setDisplayName("§cCelulle de contention");
		cont.setItemMeta(cmeta);
		
		ItemStack player = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		SkullMeta pmeta = (SkullMeta) player.getItemMeta();
		pmeta.setOwner(target.getName());
		pmeta.setDisplayName("§eRetour au menu de "+target.getName());
		player.setItemMeta(pmeta);
		
		inv.addItem(normal, cont);
		inv.setItem(26, player);
	}

	public void openMenu(Player player) {
		player.openInventory(inv);
	}

	@SuppressWarnings("unused")
	public void createInvSee(Player target) {
		
		for (int i = 0; i < 36; i++) {
			if (target.getInventory().getItem(i) != null) {
				inv.setItem(i, target.getInventory().getItem(i));
			}
		}

		inv.setItem(36, target.getInventory().getHelmet());
		inv.setItem(37, target.getInventory().getChestplate());
		inv.setItem(38, target.getInventory().getLeggings());
		inv.setItem(39, target.getInventory().getBoots());
		inv.setItem(40, target.getInventory().getItem(40));
		
		Inventory targetinv = target.getInventory();
		targetinv = inv;
		
	}
	
	public Inventory getInv(){
		return inv;
	}

}
