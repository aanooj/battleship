package com.battleship.domain.model.player;

import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.ship.Ship;

/**
 * This is player entity with unique ID and Game ID.
 * This entity will have his ship coordinates once player places the ship. 
 * Once player places the ship on his BattleG
 * round, Player is ready for the game.
 * 
 * @author pmalsh
 *
 */
public class Player {

	// This needs to be replaced with JPA/Database.
	private static int autoIncrementPlayerID = 1;
	
	private int playerID;
	
	private String playerName;
	
	private int gameID;
	
	private boolean playerIsGameReady;

	private Ship ship;
	
	private int boardId;

	/**
	 * This is default player constructor with ID and Name. 
	 * Once Player registers into the system, unique ID will be assigned.
	 * 
	 * @param lId
	 * @param lPlayerName
	 */
	public Player(String lPlayerName) throws InvalidPlayerException{
		super();
		if(lPlayerName == null || lPlayerName.isEmpty()){
			throw new InvalidPlayerException();
		}
		this.setPlayerID(autoIncrementPlayerID++);
		this.setPlayerName(lPlayerName);
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Ship getShip(){
		return this.ship;
	}
	
	public void setShip(Ship newPlayerShip) {
		if(newPlayerShip != null){
			this.ship = newPlayerShip;
			this.playerIsGameReady = true;
		}		
	}
	
	public boolean isPlayerIsGameReady() {
		return playerIsGameReady;
	}

	public void setPlayerIsGameReady(boolean playerIsGameReady) {
		this.playerIsGameReady = playerIsGameReady;
	}

	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", playerName=" + playerName + ", ship="
				+ ship + ", playerIsGameReady=" + playerIsGameReady + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + playerID;
		return result;
	}

	/**
	 * Each Player is unique with ID.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerID != other.playerID)
			return false;
		else
			return true;
	}

	/**
	 * @return the boardId
	 */
	public int getBoardId() {
		return boardId;
	}

	/**
	 * @param boardId the boardId to set
	 */
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	

}
