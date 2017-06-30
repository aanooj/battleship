package com.battleship.domain.model.ship;


public class Ship {

	// This needs to be replaced with JPA/Database.
	private static int autoIncrementShipID = 1;

	private int shipID;

	private boolean isDestroyed;

	public Ship() {
		this.setShipID(autoIncrementShipID++);
		this.setDestroyed(false);
	}

	public int getShipID() {
		return shipID;
	}

	public void setShipID(int shipID) {
		this.shipID = shipID;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

}
