package com.battleship.interfaces.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author amall3
 *
 */
public class NewPlayerRequestDTO {

	@NotBlank(message = "Player Name is mandatory.")
	private String playerName;

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName
	 * 
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}
