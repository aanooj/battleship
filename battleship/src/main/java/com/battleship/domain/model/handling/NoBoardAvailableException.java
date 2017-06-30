package com.battleship.domain.model.handling;

import static com.battleship.domain.model.handling.DomainConstants.NO_BOARD_AVAILABLE;

public class NoBoardAvailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoBoardAvailableException() {
		super(NO_BOARD_AVAILABLE);
	}

}
