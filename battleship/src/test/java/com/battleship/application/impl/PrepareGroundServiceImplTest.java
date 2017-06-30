/**
 * 
 */
package com.battleship.application.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.battleship.application.PrepareGroundService;
import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.handling.NoBoardAvailableException;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.handling.NoPlayerFoundException;
import com.battleship.domain.model.player.Player;
import com.battleship.infrastructure.BattleShipBoardRepository;
import com.battleship.infrastructure.BattleShipGameRepository;

/**
 * @author mborgo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PrepareGroundServiceImplTest {
	
	@Autowired
	private PrepareGroundService prepareGroundService;
	
	@MockBean
	private BattleShipGameRepository gameRepository;
	
	@MockBean
	private BattleShipBoardRepository battleShipBoardRepository;
	
	
	/**
	 * Test method for {@link com.battleship.application.impl.PrepareGroundServiceImpl#setShipCoodinatesForPlayer(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws InvalidPlayerException 
	 * @throws NoBoardAvailableException 
	 * @throws NoPlayerFoundException 
	 * @throws NoGameAvailableException 
	 */
	@Test
	public void testSetShipCoodinatesForPlayer() throws InvalidPlayerException, NoGameAvailableException, NoPlayerFoundException, NoBoardAvailableException {
		Player player1 = new Player("Player1");
		Player player2 = new Player("Player2");
		
		Board board = new Board();
		player1.setBoardId(board.getBoardID());
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		Game game = new Game();
		game.setGamePlayers(playerList);

		Mockito.when(gameRepository.getGameByID(game.getGameID())).thenReturn(game);
		Mockito.when(battleShipBoardRepository.getBoardByID(board.getBoardID())).thenReturn(board);
		
		String shipCoordinates = "10,11,12";
		Player player = prepareGroundService.setShipCoodinatesForPlayer(String.valueOf(game.getGameID()), String.valueOf(player1.getPlayerID()), shipCoordinates );
		assertNotNull(player);
		
	}

	/**
	 * Test method for {@link com.battleship.application.impl.PrepareGroundServiceImpl#getPlayerDetails(java.lang.String)}.
	 * @throws NoGameAvailableException 
	 * @throws InvalidPlayerException 
	 */
	@Test
	public void testGetPlayerDetails() throws NoGameAvailableException, InvalidPlayerException {
		Game game = new Game();
		Player player1 = new Player("Player1");
		Player player2 = new Player("Player2");
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		game.setGamePlayers(playerList);
		
		Mockito.when(gameRepository.getGameByID(game.getGameID())).thenReturn(game);
		List<Player> players = prepareGroundService.getPlayerDetails(String.valueOf(game.getGameID()));
		assertArrayEquals(players.toArray(), playerList.toArray());
	}

}
