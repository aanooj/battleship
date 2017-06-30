package com.battleship.domain.model.handling;

/**
 * Constants class for Battle Ship Application.
 * 
 * @author amall3
 *
 */
public class DomainConstants {

	public static final String GAME_ID = "gameId";

	public static final String INVALID_PLAYER_OBJECT = "Player object is not created. Player name must be provided.";

	public static final String NO_PLAYER_FOUND = "Requested Player could not be found in the game.";

	public static final String INVALID_GAME_OBJECT = "Game object is not created. Players must be added to Game.";

	public static final String ADD_PLAYER_SUCCESS_MSG = "New Player registered in system successfully with name ";

	public static final String NO_GAME_AVAILABLE = "Currently No Game Avaialble to play at this moment. Please try submitting reaquest again.";

	public static final String NO_BOARD_AVAILABLE = "This board id does not exists in the Repository.";

	/**
	 * This can be moved to configurations.
	 */
	public static final int DEFAULT_SHIP_SIZE = 3;

	/**
	 * This can be moved to configurations.
	 */
	public static final int DEFAULT_BOARD_SIZE = 7;

	private DomainConstants() {
	}

}
