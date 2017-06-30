/**
 * 
 */
package com.battleship.application;

import com.battleship.application.dto.HitOpponentShipDTO;
import com.battleship.application.dto.HitOpponentShipUpdateDTO;
import com.battleship.application.dto.TurnStatusDTO;
import com.battleship.domain.model.handling.NoBoardAvailableException;
import com.battleship.domain.model.handling.NoGameAvailableException;


/**
 * 
 * @author amall3
 *
 */
public interface PlayGameService {
	
	/**
	 * System maintains the turn status of individual players for respective game.
	 * This service is to check turn status with system. UI should enable play based on this status.
	 * If Game is over due to opponent's hit then it will return Game Over status as well. 
	 *  
	 * 
	 * @param checkTurnStatusRequest
	 * @param response
	 * @return GAME_OVER_STATUS if Game is "over" else TURN_STATUS_FALSE "false" or TURN_STATUS_TRUE "true"
	 * @throws NoGameAvailableException 
	 * @throws NoBoardAvailableException 
	 * @throws NumberFormatException 
	 */
	TurnStatusDTO checkPlayersTurnOrGameOverStatus(int gameId, int playerId) throws NoGameAvailableException, NoBoardAvailableException;
	
	
	/**
	 * 
	 * @param hitOpponentShipUpdateRequest
	 * @return
	 * @throws NoGameAvailableException
	 * @throws NoBoardAvailableException 
	 */

	HitOpponentShipDTO hitOpponentShipUpdateEvent(HitOpponentShipUpdateDTO hitOpponentShipUpdate) throws NoGameAvailableException, NoBoardAvailableException;
}