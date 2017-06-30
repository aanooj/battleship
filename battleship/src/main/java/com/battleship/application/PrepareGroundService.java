package com.battleship.application;

import java.util.List;

import com.battleship.domain.model.handling.NoBoardAvailableException;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.handling.NoPlayerFoundException;
import com.battleship.domain.model.player.Player;

/**
 * This is the Prepare Ground Service. 
 * Provides services for preparing battleground.
 * 
 * @author amall3
 *
 */
public interface PrepareGroundService {

	/**
	 * Player can place his ship using this service and prepare his battleground.
	 * 
	 * @param gameId
	 * @param playerId
	 * @param shipCoordinates
	 * @return <code>Player</code>
	 * @throws NoBoardAvailableException 
	 * @throws <code>NoGameAvailableException</code>, <code>NoPlayerFoundException</code>
	 */
	Player setShipCoodinatesForPlayer(String gameId, String playerId, String shipCoordinates) throws NoGameAvailableException, NoPlayerFoundException, NoBoardAvailableException;

	
	/**
	 * This service returns List of players for gameID.
	 * 
	 * @param gameId
	 * @return <code>List</code> of players
	 * 
	 * @throws <code>NoGameAvailableException</code>
	 */
	List<Player> getPlayerDetails(String gameId) throws NoGameAvailableException;

}