/**
 * 
 */
package com.battleship.interfaces.dto;


/**
 * @author amall3
 *
 */
public class HitOpponentShipUpdateResponseDTO extends ResponseDTO {

	private String opponentHitCoordinates;

	private String opponentMissCoordinates;

	private boolean isGameOver;

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public String getOpponentHitCoordinates() {
		return opponentHitCoordinates;
	}

	public void setOpponentHitCoordinates(String opponentHitCoordinates) {
		this.opponentHitCoordinates = opponentHitCoordinates;
	}

	public String getOpponentMissCoordinates() {
		return opponentMissCoordinates;
	}

	public void setOpponentMissCoordinates(String opponentMissCoordinates) {
		this.opponentMissCoordinates = opponentMissCoordinates;
	}

}
