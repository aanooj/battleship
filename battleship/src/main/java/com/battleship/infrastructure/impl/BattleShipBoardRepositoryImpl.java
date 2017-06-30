package com.battleship.infrastructure.impl;

import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.handling.NoBoardAvailableException;
import com.battleship.infrastructure.BattleShipBoardRepository;

/**
 * This repository manages <code>Board</code> instances. This repository can be
 * replaced JPA/Database Implementation.
 * 
 * 
 * @author pmalsh
 *
 */
@Component
public class BattleShipBoardRepositoryImpl implements BattleShipBoardRepository {

	/**
	 * This is LinkedList repository for easy retrieval of boards.
	 * 
	 */
	private LinkedList<Board> allBoards;

	// Changing this constructor from private to public as Mockito is unable to
	// call this default constructor while creating mock object.
	// Only for testing purpose until solution available.
	public BattleShipBoardRepositoryImpl() {
		allBoards = new LinkedList<Board>();
	}

	/**
	 * This implements creation a new Board Service.
	 * 
	 * @return Board
	 */
	public Board getNewBoard() {
		Board newBoard = new Board();
		allBoards.push(newBoard);
		return newBoard;
	}

	/**
	 * This implements retrieval of <code>Board</code> by boardId.
	 * 
	 * @return <code>Board<code>
	 */
	public Board getBoardByID(int boardId) throws NoBoardAvailableException {
		for (Board board : allBoards) {
			if (board.getBoardID() == boardId) {
				return board;
			}
		}
		throw new NoBoardAvailableException();
	}
}
