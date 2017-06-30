package com.battleship.infrastructure;

import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.player.Player;


/**
 * This is repository service for creation and maintaining game for multiple pairs of players.
 * 
 * @author pmalsh
 *
 */
public interface BattleShipGameRepository {
	
	
	/**
	 * This method retrieves latest Game ID if available for play.
	 * If there is no game available the it creates new game and provides new Game ID.
	 * 
	 * @return Game ID 
	 */
	public int latestAvailableGame();
	
	/**
	 * This method returns Game for provided Game ID.
	 * If Game is not available, NULL will be returned.
	 * 
	 * @return <code>Game<code>
	 */
	public Game getGameByID(int gameID) throws NoGameAvailableException;
	
	/**
	 * This method adds player to the game if that game has less than 2 players.
	 *  
	 * @param player
	 * @param gameID
	 * @return true if player added successfully, false if fails to add player into game
	 * @throws NoGameAvailableException 
	 */
	public boolean addNewPlayerByGameID(Player player, int gameID) throws NoGameAvailableException;
	
	
}
