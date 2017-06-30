/**
 * 
 */
package com.battleship.application.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.battleship.application.PlayGameService;
import com.battleship.application.dto.HitOpponentShipDTO;
import com.battleship.application.dto.HitOpponentShipUpdateDTO;
import com.battleship.application.dto.TurnStatusDTO;
import com.battleship.application.util.GenericUtil;
import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.handling.NoBoardAvailableException;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.player.Player;
import com.battleship.domain.model.ship.Ship;
import com.battleship.infrastructure.BattleShipBoardRepository;
import com.battleship.infrastructure.BattleShipGameRepository;

/**
 * @author mborgo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayGameServiceImplTest {

	@Autowired
	private PlayGameService playGameService;  
	
	@MockBean
	private BattleShipGameRepository gameRepository;
	
	@MockBean
	private BattleShipBoardRepository battleShipBoardRepository;
	
	@Autowired
	private GenericUtil genericUtil;
	
	@Before
	public void setUp() throws NoGameAvailableException, InvalidPlayerException {
		
	}
	@Test
	public void testCheckPlayersTurnOrGameOverStatus_whenGameOver() throws NoGameAvailableException, InvalidPlayerException, NoBoardAvailableException {
		Game game = new Game();
		Player player = new Player("player");
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player);
		game.setGamePlayers(playerList);
		Ship ship = new Ship();
		ship.setDestroyed(true);
		player.setShip(ship);
		Board board = new Board();
		player.setBoardId(board.getBoardID());
		Mockito.when(gameRepository.getGameByID(game.getGameID())).thenReturn(game);
		Mockito.when(battleShipBoardRepository.getBoardByID(board.getBoardID())).thenReturn(board);
		TurnStatusDTO turnStatusDTO = playGameService.checkPlayersTurnOrGameOverStatus(game.getGameID(), player.getPlayerID());
		assertNotNull(turnStatusDTO);
		assertTrue(turnStatusDTO.isGameOver());
	}

	@Test
	public void testCheckPlayersTurnOrGameOverStatus_playerTurn1() throws NoGameAvailableException, InvalidPlayerException, NoBoardAvailableException {
		Game game = new Game();
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		game.setGameID(1);
		game.setGamePlayers(playerList);
		game.setWhoseTurnPlayerId(player1.getPlayerID());
		Ship ship = new Ship();
		ship.setDestroyed(false);
		player1.setShip(ship);
		player2.setShip(ship);
		
		Board board = new Board();
		player1.setBoardId(board.getBoardID());
		Mockito.when(battleShipBoardRepository.getBoardByID(board.getBoardID())).thenReturn(board);
		
		Mockito.when(gameRepository.getGameByID(game.getGameID())).thenReturn(game);
		TurnStatusDTO turnStatusDTO = playGameService.checkPlayersTurnOrGameOverStatus(game.getGameID(), player1.getPlayerID());
		assertNotNull(turnStatusDTO);
		assertTrue(turnStatusDTO.isTurnStatus());
	}

	@Test
	public void testCheckPlayersTurnOrGameOverStatus_playerTurn2() throws NoGameAvailableException, InvalidPlayerException, NoBoardAvailableException {
		Game game = new Game();
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		game.setGameID(1);
		game.setGamePlayers(playerList);
		game.setWhoseTurnPlayerId(player2.getPlayerID());
		Ship ship = new Ship();
		ship.setDestroyed(false);
		player1.setShip(ship);
		player2.setShip(ship);

		Board board = new Board();
		player2.setBoardId(board.getBoardID());
		Mockito.when(battleShipBoardRepository.getBoardByID(board.getBoardID())).thenReturn(board);

		Mockito.when(gameRepository.getGameByID(game.getGameID())).thenReturn(game);
		TurnStatusDTO turnStatusDTO = playGameService.checkPlayersTurnOrGameOverStatus(game.getGameID(), player2.getPlayerID());
		assertNotNull(turnStatusDTO);
		assertTrue(turnStatusDTO.isTurnStatus());
	}
	
	@Test
	public void testHitOpponentShipUpdateEvent_whenPlayer1HitShip() throws InvalidPlayerException, NoGameAvailableException, NoBoardAvailableException {
		Game game = new Game();
		Player player1 = new Player("player1");
		Player player2 = new Player("player2");
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		game.setGameID(1);
		game.setGamePlayers(playerList);
		game.setWhoseTurnPlayerId(player2.getPlayerID());
		Ship ship = new Ship();
		ship.setDestroyed(false);
		player1.setShip(ship);
		player2.setShip(ship);
		
		Board board1 = new Board();
		board1.setShipPositionCoordinateList(genericUtil.convertToListOfArrayIntegers("10,11,12"));
		player1.setBoardId(board1.getBoardID());

		Board board2 = new Board();
		board2.setShipPositionCoordinateList(genericUtil.convertToListOfArrayIntegers("54,55,56"));
		player2.setBoardId(board2.getBoardID());

		
		Mockito.when(battleShipBoardRepository.getBoardByID(board1.getBoardID())).thenReturn(board1);
		Mockito.when(battleShipBoardRepository.getBoardByID(board2.getBoardID())).thenReturn(board2);
		Mockito.when(gameRepository.getGameByID(game.getGameID())).thenReturn(game);
		HitOpponentShipUpdateDTO hitOpponentShipUpdate = new HitOpponentShipUpdateDTO();
		hitOpponentShipUpdate.setGameId(String.valueOf(game.getGameID()));
		hitOpponentShipUpdate.setPlayerId(String.valueOf(player1.getPlayerID()));
		String hitCoordinate = "56";
		hitOpponentShipUpdate.setHitCoordinate(hitCoordinate );
		
		HitOpponentShipDTO hitOpponentShipDTO = playGameService.hitOpponentShipUpdateEvent(hitOpponentShipUpdate);
		assertNotNull(hitOpponentShipDTO);
		assertEquals(hitCoordinate, hitOpponentShipDTO.getOpponentHitCoordinates());
	}
}
