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
import com.battleship.domain.model.player.Player;
import com.battleship.domain.model.ship.Ship;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipApplication.class})
public class PlayerDomainUnitTests {

	private Player newPlayer;
	private String playerName;
	private Game game;
	private int gameID;
	private Ship newPlayerShip;
	private Player secondPlayer;
	
	@Before
	public void setUp() throws Exception {
		
		playerName = "TestPlayer";
		newPlayer = new Player(playerName);	
		game = new Game();
		gameID = game.getGameID();
		newPlayerShip = new Ship();
		
		secondPlayer = new Player(playerName);	
	}

	@Test
	public void testNewPlayerCreation() {
		
		if(newPlayer != null){
			assertTrue(newPlayer.getPlayerName().equals(playerName));
			assertTrue(newPlayer.getPlayerID() > 0);
		}
		else
			assertFalse(true);		
	}
	
	@Test
	public void testPlayerUpdateWithGameID() {
		
		newPlayer.setGameID(game.getGameID());
		
		if(newPlayer != null){
			assertTrue(newPlayer.getGameID() == gameID);
		}
		else
			assertFalse(true);		
	}
	
	@Test
	public void testPlayerUpdateWithShipDetails() {
		
		newPlayer.setShip(newPlayerShip);
		
		if(newPlayer != null){
			assertTrue(newPlayer.getShip().getShipID() > 0);
			//assertTrue(newPlayer.getShip().getPlayerShipCoordinates().isEmpty());
			assertTrue(newPlayer.isPlayerIsGameReady());
		}
		else
			assertFalse(true);		
	}
	
	@Test
	public void testPlayerUpdateWithNullShipDetails() {
		
		newPlayer.setShip(null);
		
		if(newPlayer != null){
			assertTrue(newPlayer.getShip() == null);
			assertTrue(!newPlayer.isPlayerIsGameReady());
		}
		else
			assertFalse(true);		
	}
	
	
	@Test
	public void testPlayerForEqualsAndHash() {

		if (!newPlayer.equals(secondPlayer)) {
			assertTrue(newPlayer.getPlayerID() != secondPlayer.getPlayerID());
		} else
			assertFalse(true);
		
		if (newPlayer.hashCode() != secondPlayer.hashCode()) {
			assertTrue(newPlayer.getPlayerID() != secondPlayer.getPlayerID());
		} else
			assertFalse(true);
		
	}
}
