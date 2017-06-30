package com.battleship.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.battleship.BattleshipApplication;
import com.battleship.domain.model.game.Game;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipApplication.class})
public class GameDomainUnitTests {

	private Game newGame;
	
	@Before
	public void setUp() throws Exception {
		
		newGame = new Game();		
	}

	@Test
	public void testGameCreation() {
		
		if(newGame != null){
			assertTrue(newGame.getGameID() > 0);
			assertTrue(newGame.getGamePlayers().isEmpty());
		}
		else
			assertFalse(true);		
	}
		
}
