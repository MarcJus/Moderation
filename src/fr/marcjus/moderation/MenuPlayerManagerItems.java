package fr.marcjus.moderation;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum MenuPlayerManagerItems {
	
	FREZZE("§9Frezze le joueur", new ItemStack(Material.PACKED_ICE)),
	GLOWING("§eFaire briller le joueur", new ItemStack(Material.GLOWSTONE_DUST)),
	INVSEE("§aMontre l'inventaire du joueur", new ItemStack(Material.CHEST)),
	ENDERSEE("§2Montre l'enderchest du joueur", new ItemStack(Material.ENDER_CHEST)),
	TP("§7Se téléporter au joueur", new ItemStack(Material.ENDER_PEARL)),
	GOD("§cRend le joueur invincible", new ItemStack(Material.DIAMOND_CHESTPLATE));
	
	private ItemStack it;
	private String displayName;
	
	private MenuPlayerManagerItems(String displayName, ItemStack mat){
		setDisplayName(displayName);
		setIt(mat);
	}
	
	public ItemStack getItem(){
		ItemStack i = it;
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(displayName);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		i.setItemMeta(meta);
		return it;
	}
	
	public ItemStack getIt(){
		return it;
	}
	
	public void setIt(ItemStack it){
		this.it = it;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


}
