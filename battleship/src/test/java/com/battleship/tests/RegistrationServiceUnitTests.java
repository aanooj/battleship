package com.battleship.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.battleship.BattleshipApplication;
import com.battleship.application.impl.RegistrationServiceImpl;
import com.battleship.infrastructure.BattleShipGameRepository;
import com.battleship.infrastructure.impl.BattleShipGameRepositoryImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipApplication.class})
public class RegistrationServiceUnitTests {
	
	//@Mock  Disabling this mock as MOckito is unable to inject this mock by calling default constructor.
	@InjectMocks
	private BattleShipGameRepository gameRepository  = new BattleShipGameRepositoryImpl();
	
	@InjectMocks
	private RegistrationServiceImpl service;
	
	@Before
	public void init(){
		//gameRepository = Mockito.mock(BattleShipGameRepository.class);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testRetrieveLatestAvailableGame() { 
		
//		Game game = null;
//		try {
//			game = service.retrieveLatestAvailableGame();
//			System.out.println("=================== >>>>>>>>>>> 1111gamegamegamegame "+game);
//		} catch (GameInitiationException | NoGameAvailableException e) {
//			System.out.println("=================== >>>>>>>>>>> "+e);
//		}
//	
//		System.out.println("=================== >>>>>>>>>>> 222gamegamegamegame "+game);
//		
//		if(game != null){
//			assertTrue(game.getGameID() > 0);
//			assertTrue(game.getGamePlayers().isEmpty());
//		}
//		else
//			assertFalse(true);
		
	}

	
	@Test
	public void testSubsequentGameCreation() {
		//TODO
	}
	
}
