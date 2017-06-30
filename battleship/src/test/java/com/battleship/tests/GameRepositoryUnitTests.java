package com.battleship.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.battleship.BattleshipApplication;
import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.player.Player;
import com.battleship.infrastructure.BattleShipGameRepository;
import com.battleship.infrastructure.impl.BattleShipGameRepositoryImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BattleshipApplication.class })
public class GameRepositoryUnitTests {

	@InjectMocks
	private BattleShipGameRepository gameRepository = new BattleShipGameRepositoryImpl();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFirstGameCreation() {

		int latestGameID = gameRepository.latestAvailableGame();

		Game newGame = null;
		try {
			newGame = gameRepository.getGameByID(latestGameID);
		} catch (NoGameAvailableException e) {
			e.printStackTrace();
		}

		if (newGame != null) {
			assertTrue(newGame.getGamePlayers().isEmpty());
		} else
			assertFalse(true);

	}

	@Test
	public void testSubsequentGameCreation() {

		int latestGameID = gameRepository.latestAvailableGame();

		Game newGame = null;
		try {
			newGame = gameRepository.getGameByID(latestGameID);
		} catch (NoGameAvailableException e) {
			e.printStackTrace();
		}

		if (newGame != null) {
			assertTrue(newGame.getGamePlayers().isEmpty());
		} else
			assertFalse(true);
	}

	@Test
	public void testNoGameAvailableException() {
		try {
			Game game = gameRepository.getGameByID(100);
			assertFalse(true);
		} catch (NoGameAvailableException e) {
			assertTrue(e
					.getMessage()
					.equals(com.battleship.domain.model.handling.DomainConstants.NO_GAME_AVAILABLE));
		}
	}

	@Test
	public void testAddNewPlayerByGameID() {

		int latestGameID = gameRepository.latestAvailableGame();
		try {
			boolean success = gameRepository.addNewPlayerByGameID(new Player("Test"), latestGameID);
			assertTrue(success);
		} catch (NoGameAvailableException | InvalidPlayerException e) {
			assertFalse(true);
		}
	}
	
	
	@Test
	public void testRejectExtraPlayerByGameID() {

		int latestGameID = gameRepository.latestAvailableGame();
		try {
			gameRepository.addNewPlayerByGameID(new Player("Test"), latestGameID);
			gameRepository.addNewPlayerByGameID(new Player("ABC"), latestGameID);
			boolean success = gameRepository.addNewPlayerByGameID(new Player("EFG"), latestGameID);
			assertTrue(!success);
		} catch (NoGameAvailableException | InvalidPlayerException e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testLatestGameAvailable() {

		int latestGameID = gameRepository.latestAvailableGame();
		try {
			gameRepository.addNewPlayerByGameID(new Player("Test"), latestGameID);
			int gameId = gameRepository.latestAvailableGame();
			assertTrue(latestGameID == gameId);
		} catch (NoGameAvailableException | InvalidPlayerException e) {
			assertFalse(true);
		}
	}

}
