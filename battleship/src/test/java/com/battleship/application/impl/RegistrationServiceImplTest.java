/**
 * 
 */
package com.battleship.application.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.battleship.application.RegistrationService;
import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.GameInitiationException;
import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.infrastructure.BattleShipBoardRepository;
import com.battleship.infrastructure.BattleShipGameRepository;

/**
 * @author mborgo
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceImplTest {

	@Autowired
	RegistrationService registrationService;
	
	@MockBean
	private BattleShipGameRepository gameRepository;

	@MockBean
	private BattleShipBoardRepository battleShipBoardRepository;

	/**
	 * Test method for {@link com.battleship.application.impl.RegistrationServiceImpl#retrieveLatestAvailableGame()}.
	 * @throws NoGameAvailableException 
	 * @throws GameInitiationException 
	 */
	@Test
	public void testRetrieveLatestAvailableGame() throws NoGameAvailableException, GameInitiationException {
		Game game = new Game();
		Mockito.when(gameRepository.latestAvailableGame()).thenReturn(game.getGameID());	
		Mockito.when(gameRepository.getGameByID(game.getGameID())).thenReturn(game);
		Game latestGame = registrationService.retrieveLatestAvailableGame();
		assertNotNull(latestGame);
		assertSame(game.getGameID(),latestGame.getGameID());
	}

	/**
	 * Test method for {@link com.battleship.application.impl.RegistrationServiceImpl#registerNewPlayer(java.lang.String)}.
	 * @throws NoGameAvailableException 
	 * @throws GameInitiationException 
	 * @throws InvalidPlayerException 
	 */
	@Test(expected=NoGameAvailableException.class)
	public void testRegisterNewPlayer_whenNoGameAvailable() throws InvalidPlayerException, GameInitiationException, NoGameAvailableException {
		Game game = new Game();
		Mockito.when(gameRepository.latestAvailableGame()).thenReturn(game.getGameID());	
		Mockito.when(gameRepository.getGameByID(game.getGameID())).thenReturn(game);			
		Board board = new Board();
		Mockito.when(battleShipBoardRepository.getNewBoard()).thenReturn(board);
		registrationService.registerNewPlayer("Player1");
		
	}

}
