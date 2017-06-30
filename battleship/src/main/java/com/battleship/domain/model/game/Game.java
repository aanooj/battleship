package com.battleship.domain.model.game;

import java.util.ArrayList;
import java.util.List;

import com.battleship.domain.model.player.Player;

/**
 * Pair of players will be assigned with <code>Game</code> with Unique Game ID
 * Game entity should contain only 2 players.
 *  
 * @author pmalsh
 *
 */
public class Game {

	// This needs to be replaced with JPA/Database.
	private static int autoIncrementGameID = 1;
	
	/**
	 * This is unique Game ID for each pair of players.
	 */	
	private int gameID;


	/**
	 * This is list of 2 <code>Player</code> entities.
	 * 
	 */
	private List<Player> gamePlayers;
	
	/**
	 * This indicates whose turn is it to hit enemy ship.
	 * 
	 */
	private int whoseTurnPlayerId;
	
	
	public Game(){
		super();
		this.setGameID(autoIncrementGameID++);
		this.setGamePlayers(new ArrayList<Player>());	
	}
	
	
	public int getGameID() {
		return gameID;
	}


	public void setGameID(int gameID) {
		this.gameID = gameID;
	}


	public List<Player> getGamePlayers() {
		return gamePlayers;
	}


	public void setGamePlayers(List<Player> gamePlayers) {
		this.gamePlayers = gamePlayers;
	}


	public int getWhoseTurnPlayerId() {
		return whoseTurnPlayerId;
	}


	public void setWhoseTurnPlayerId(int whoseTurnPlayerId) {
		this.whoseTurnPlayerId = whoseTurnPlayerId;
	}
	
}
