package com.battleship.domain.model.handling;

import static com.battleship.domain.model.handling.DomainConstants.INVALID_GAME_OBJECT;

public class GameInitiationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public GameInitiationException() {
        super(INVALID_GAME_OBJECT);
    }

}
