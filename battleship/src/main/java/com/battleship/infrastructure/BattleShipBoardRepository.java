package com.battleship.infrastructure;

import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.handling.NoBoardAvailableException;

/**
 * This is repository service for creation and maintaining game for multiple
 * pairs of players.
 * 
 * @author pmalsh
 *
 */
public interface BattleShipBoardRepository {

	/**
	 * This service is responsible for creating Board.
	 * 
	 * @return Board
	 */
	Board getNewBoard();

	/**
	 * This service is responsible for retrieving Board by Id.
	 * 
	 * @param boardId
	 * @return Board
	 * @throws NoBoardAvailableException
	 */
	Board getBoardByID(int boardId) throws NoBoardAvailableException;

}
