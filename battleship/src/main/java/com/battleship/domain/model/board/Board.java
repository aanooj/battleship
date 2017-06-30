/**
 * 
 */
package com.battleship.domain.model.board;

import java.util.ArrayList;
import java.util.List;

import com.battleship.domain.model.handling.DomainConstants;

/**
 * @author amall3
 *
 */

public class Board {

	// This needs to be replaced with JPA/Database.
	private static int autoIncrementBoardID = 1;

	private int boardID;

	private int[][] boardSize;

	private List<int[][]> shipPositionCoordinateList;

	private List<int[][]> hitCoordinateList;

	private List<int[][]> missCoordinateList;

	public Board() {
		super();
		this.setBoardID(autoIncrementBoardID++);
		this.boardSize = new int[DomainConstants.DEFAULT_BOARD_SIZE][DomainConstants.DEFAULT_BOARD_SIZE];
		this.hitCoordinateList = new ArrayList<int[][]>(DomainConstants.DEFAULT_SHIP_SIZE);
		this.missCoordinateList = new ArrayList<int[][]>();
	}

	/**
	 * @return the boardID
	 */
	public int getBoardID() {
		return boardID;
	}

	/**
	 * @param boardID
	 *            the boardID to set
	 */
	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}

	/**
	 * @return the boardSize
	 */
	public int[][] getBoardSize() {
		return boardSize;
	}

	/**
	 * @param boardSize
	 *            the boardSize to set
	 */
	public void setBoardSize(int[][] boardSize) {
		this.boardSize = boardSize;
	}

	public List<int[][]> getShipPositionCoordinateList() {
		return shipPositionCoordinateList;
	}

	public void setShipPositionCoordinateList(
			List<int[][]> shipPositionCoordinateList) {
		this.shipPositionCoordinateList = shipPositionCoordinateList;
	}

	public List<int[][]> getHitCoordinateList() {
		return hitCoordinateList;
	}

	public void setHitCoordinateList(List<int[][]> hitCoordinateList) {
		this.hitCoordinateList = hitCoordinateList;
	}

	public List<int[][]> getMissCoordinateList() {
		return missCoordinateList;
	}

	public void setMissCoordinateList(List<int[][]> missCoordinateList) {
		this.missCoordinateList = missCoordinateList;
	}

}
