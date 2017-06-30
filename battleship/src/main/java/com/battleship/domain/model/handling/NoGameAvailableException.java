package com.battleship.domain.model.handling;

import static com.battleship.domain.model.handling.DomainConstants.NO_GAME_AVAILABLE;

public class NoGameAvailableException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	
	public NoGameAvailableException() {
        super(NO_GAME_AVAILABLE);
    }

}
