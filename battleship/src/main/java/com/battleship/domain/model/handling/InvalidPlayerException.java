package com.battleship.domain.model.handling;

import static com.battleship.domain.model.handling.DomainConstants.INVALID_PLAYER_OBJECT;

/**
 * Custom exception represents Invalid state of <code>Player<code> entity.
 * 
 * @author pmalsh
 *
 */


public class InvalidPlayerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public InvalidPlayerException() {
        super(INVALID_PLAYER_OBJECT);
    }

}
