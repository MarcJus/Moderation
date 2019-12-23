package fr.marcjus.moderation.manager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class JailManager {

	private static World world = Bukkit.getWorld("world");
	private static ArrayList<Location> locs = new ArrayList<>();
	private static ArrayList<Player> prisonners = new ArrayList<>();
	private static int size = prisonners.size();
	private static int s = size --;
	private static ArrayList<Player> containments = new ArrayList<>();

	public static boolean isPrisonner(Player target) {
		if (prisonners.contains(target)) {
			return true;
		}
		return false;
	}

	public static void addPrisoner(Player target) {
		if(locs.isEmpty()){
			addLocs();
		}
		
		if(isContainmentPrisonner(target)){
			containments.remove(target);
		}
		
		target.teleport(locs.get(s));
		prisonners.add(target);
		target.sendMessage("§cVous etes en prison !");
		target.setGameMode(GameMode.ADVENTURE);
		target.getInventory().clear();
	}

	public static boolean removePrisonner(Player target) {
		if (locs.get(s) == null) {
			return false;
		}
		prisonners.remove(target);
		target.sendMessage("§aVous n'etes plus en prison !");
		target.teleport(new Location(world, -382.5, 61, 411.5, -90, 0));
		target.setGameMode(GameMode.CREATIVE);
		return true;
	}

	public static void addLocs() {
		if (locs.isEmpty()) {
			locs.add(new Location(world, -386.5, 57.5, 402.5, 0f, 0f));
			locs.add(new Location(world, -381.5, 57.5, 402.5, 0f, 0f));
			locs.add(new Location(world, -377.5, 57.5, 405.5, 90f, 0f));
			locs.add(new Location(world, -377.5, 57.5, 410.5, 90f, 0f));
		}
	}

	public static ArrayList<Location> getLocs() {
		return locs;
	}

	public static void addContainmentPrisonner(Player target) {
		if(isPrisonner(target)){
			prisonners.remove(target);
		}
		if(!containments.contains(target)){
			target.teleport(new Location(world, -385, 57, 420, 180f, 0f));
			target.setGameMode(GameMode.ADVENTURE);
			target.setHealth(20);
			target.setFoodLevel(20);
			target.setExp(0f);
			target.setLevel(0);
			containments.add(target);
			target.getInventory().clear();
		}
	}
	public static void removeContainmentPrisonner(Player target) {
		if(containments.contains(target)){
			containments.remove(target);
			target.teleport(new Location(world, -382.5, 61.5, 411.5, -90, 0));
			target.setGameMode(GameMode.CREATIVE);
		}
	}
	
	public static boolean isContainmentPrisonner(Player player){
		return containments.contains(player);
	}

}