package fr.marcjus.moderation.manager;

public class PlayerManager {
	
	private boolean oneshot = false;
	private boolean god = false;
	private boolean frezze = false;
	private boolean glow = false;
	private boolean speed = false;
	
	public PlayerManager() {
	}

	public boolean isOneshot() {
		return oneshot;
	}

	public void setOneshot(boolean oneshot) {
		this.oneshot = oneshot;
	}

	public boolean isGod() {
		return god;
	}

	public void setGod(boolean god) {
		this.god = god;
	}

	public boolean isFrezze() {
		return frezze;
	}

	public void setFrezze(boolean frezze) {
		this.frezze = frezze;
	}

	public boolean isGlow() {
		return glow;
	}

	public void setGlow(boolean glow) {
		this.glow = glow;
	}

	public boolean isSpeed() {
		return speed;
	}

	public void setSpeed(boolean speed) {
		this.speed = speed;
	}

}
