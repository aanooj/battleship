/**
 * 
 */
package com.battleship.interfaces.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author amall3
 *
 */
public class PlaceShipRequestDTO {

	@NotBlank(message = "Game id is mandatory.")
	private String gameId;

	@NotBlank(message = "Player Id is mandatory.")
	private String playerId;

	@NotBlank(message = "Ship Coordinates are mandatory.")
	private String shipCoordinates;

	/**
	 * @return the gameId
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the shipCoordinates
	 */
	public String getShipCoordinates() {
		return shipCoordinates;
	}

	/**
	 * @param shipCoordinates
	 *            the shipCoordinates to set
	 */
	public void setShipCoordinates(String shipCoordinates) {
		this.shipCoordinates = shipCoordinates;
	}

}
