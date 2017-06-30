/**
 * 
 */
package com.battleship.application.dto;


/**
 * @author amall3
 *
 */
public class RegisterNewPlayerDTO {

	private int playerID;

	private String playerName;

	private int gameID;

	private int boardId;

	private int boardSize;

	/**
	 * @return the boardSize
	 */
	public int getBoardSize() {
		return boardSize;
	}

	/**
	 * @param boardSize
	 *            the boardSize to set
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * @param playerID
	 *            the playerID to set
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName
	 *            the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return the gameID
	 */
	public int getGameID() {
		return gameID;
	}

	/**
	 * @param gameID
	 *            the gameID to set
	 */
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	/**
	 * @return the boardId
	 */
	public int getBoardId() {
		return boardId;
	}

	/**
	 * @param boardId
	 *            the boardId to set
	 */
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

}
