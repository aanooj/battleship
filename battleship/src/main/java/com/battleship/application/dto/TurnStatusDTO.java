/**
 * 
 */
package com.battleship.application.dto;


/**
 * @author amall3
 *
 */
public class TurnStatusDTO {

	private String myHitCoordinates;

	private String myMissCoordinates;

	private boolean isGameOver;

	private boolean turnStatus;

	/**
	 * @return the isGameOver
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * @param isGameOver
	 *            the isGameOver to set
	 */
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	/**
	 * @return the turnStatus
	 */
	public boolean isTurnStatus() {
		return turnStatus;
	}

	/**
	 * @param turnStatus
	 *            the turnStatus to set
	 */
	public void setTurnStatus(boolean turnStatus) {
		this.turnStatus = turnStatus;
	}

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

}
