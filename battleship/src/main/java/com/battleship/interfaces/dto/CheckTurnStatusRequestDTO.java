/**
 * 
 */
package com.battleship.interfaces.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author amall3
 *
 */
public class CheckTurnStatusRequestDTO {

	@NotBlank(message = "Player Id is mandatory.")
	private String playerId;

	@NotBlank(message = "Game Id is mandatory.")
	private String gameId;

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
}
