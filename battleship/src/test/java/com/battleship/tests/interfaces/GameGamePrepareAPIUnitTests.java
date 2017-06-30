package com.battleship.tests.interfaces;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.battleship.BattleshipApplication;
import com.battleship.application.PrepareGroundService;
import com.battleship.application.RegistrationService;
import com.battleship.application.dto.RegisterNewPlayerDTO;
import com.battleship.application.util.GenericUtil;
import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.player.Player;
import com.battleship.domain.model.ship.Ship;
import com.battleship.infrastructure.BattleShipBoardRepository;
import com.battleship.infrastructure.BattleShipGameRepository;
import com.battleship.infrastructure.impl.BattleShipBoardRepositoryImpl;
import com.battleship.infrastructure.impl.BattleShipGameRepositoryImpl;
import com.battleship.interfaces.web.BattleShipController;

//import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipApplication.class})
public class GameGamePrepareAPIUnitTests {

    private MockMvc mockMvc;

    @InjectMocks
    private BattleShipController battleshipController;
    
    @Mock
    private RegistrationService registrationService;

    @Mock
    private PrepareGroundService prepareService;
    
    @InjectMocks
    private BattleShipGameRepository gameRepository = new BattleShipGameRepositoryImpl();
    
    @InjectMocks
    BattleShipBoardRepository battleShipBoardRepository = new BattleShipBoardRepositoryImpl();
    
    @Mock
    private GenericUtil util;
    
    private int gameId;
    
    private Player firstPlayer;
    
    private Player secondPlayer;
    
    private Ship firstShip;
    
    private Ship secondShip;
    
    private String firstPlayerShipCoordinates;
    
    private String secondPlayerShipCoordinates;
    
    @Before
    public void init() throws InvalidPlayerException{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(battleshipController)
                .build();
        
        gameId = gameRepository.latestAvailableGame();
		
        firstShip = new Ship();
		secondShip = new Ship();
		
		firstPlayer = new Player("John");
		firstPlayer.setGameID(gameId);
		firstPlayer.setShip(firstShip);
		
		
		secondPlayer = new Player("Abhi");
		secondPlayer.setGameID(gameId);
		secondPlayer.setShip(secondShip);
		
		firstPlayerShipCoordinates = "10,11,12";
		secondPlayerShipCoordinates = "20,21,22";
		
		
    }
        
    @Test
    public void testPlaceShipService() throws Exception {
        	
    		when(prepareService.setShipCoodinatesForPlayer(String.valueOf(gameId), String.valueOf(firstPlayer.getPlayerID()), firstPlayerShipCoordinates)).thenReturn(firstPlayer);
			
    		mockMvc.perform(
			        post("/rest/api/placeShip")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{\"gameId\":\""+gameId+"\","
			        		+ "\"playerId\":\""+firstPlayer.getPlayerID()+"\","
			        		+ "\"shipCoordinates\":\""+firstPlayerShipCoordinates+"\""
			        		+ "}"))			                
					.andExpect(status().isOk());
    		
    		verify(prepareService).setShipCoodinatesForPlayer(String.valueOf(gameId), String.valueOf(firstPlayer.getPlayerID()),firstPlayerShipCoordinates);
    }
    
    
    @Test
    public void testRetrievePlayerDetailsService() throws Exception {
    	
    		List<Player> playersList = new ArrayList<Player>();
    		playersList.add(firstPlayer);
    		playersList.add(secondPlayer);
        	
    		when(prepareService.getPlayerDetails(""+gameId)).thenReturn(playersList);
			
    		mockMvc.perform(
			        post("/rest/api/retrievePlayerDetails")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{\"gameId\":\""+gameId+"\"}"))			                
					.andExpect(status().isOk());
    		
    		verify(prepareService).getPlayerDetails(""+gameId);
    }
    
    
}
