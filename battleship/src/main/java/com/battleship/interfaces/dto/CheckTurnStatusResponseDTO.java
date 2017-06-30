/**
 * 
 */
package com.battleship.interfaces.dto;


/**
 * @author amall3
 *
 */
public class CheckTurnStatusResponseDTO extends ResponseDTO {

	private String myHitCoordinates;

	private String myMissCoordinates;

	private boolean isGameOver;

	private boolean turnStatus;

	public String getMyHitCoordinates() {
		return myHitCoordinates;
	}

	public void setMyHitCoordinates(String myHitCoordinates) {
		this.myHitCoordinates = myHitCoordinates;
	}

	public String getMyMissCoordinates() {
		return myMissCoordinates;
	}

	public void setMyMissCoordinates(String myMissCoordinates) {
		this.myMissCoordinates = myMissCoordinates;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public boolean isTurnStatus() {
		return turnStatus;
	}

	public void setTurnStatus(boolean turnStatus) {
		this.turnStatus = turnStatus;
	}

	 
}