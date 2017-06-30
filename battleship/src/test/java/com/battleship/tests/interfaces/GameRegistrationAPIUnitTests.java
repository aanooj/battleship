package com.battleship.tests.interfaces;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.battleship.application.RegistrationService;
import com.battleship.application.dto.RegisterNewPlayerDTO;
import com.battleship.application.util.GenericUtil;
import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.player.Player;
import com.battleship.infrastructure.BattleShipBoardRepository;
import com.battleship.infrastructure.BattleShipGameRepository;
import com.battleship.infrastructure.impl.BattleShipBoardRepositoryImpl;
import com.battleship.infrastructure.impl.BattleShipGameRepositoryImpl;
import com.battleship.interfaces.web.BattleShipController;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BattleshipApplication.class})
public class GameRegistrationAPIUnitTests {

    private MockMvc mockMvc;

    @InjectMocks
    private BattleShipController battleshipController;
    
    @Mock
    private RegistrationService service;

    @InjectMocks
    private BattleShipGameRepository gameRepository = new BattleShipGameRepositoryImpl();
    
    @InjectMocks
    BattleShipBoardRepository battleShipBoardRepository = new BattleShipBoardRepositoryImpl();
    
    @Mock
    private GenericUtil util;
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(battleshipController)
                .build();
    }
        
    @Test
    public void testRegisterNewPlayerService() throws Exception {
        	
	    	String newPlayerName = "John";
	    	RegisterNewPlayerDTO newPlayerDTO = new RegisterNewPlayerDTO();
	    	Player newPlayer = new Player(newPlayerName);
	    	Board newBoard = battleShipBoardRepository.getNewBoard();
	    	
	    	newPlayer.setBoardId(newBoard.getBoardID());
	    	
	    	newPlayerDTO.setPlayerID(newPlayer.getPlayerID());
	    	newPlayerDTO.setBoardId(newPlayer.getBoardId());
	    	newPlayerDTO.setGameID(newPlayer.getGameID());
	    	newPlayerDTO.setPlayerName(newPlayer.getPlayerName());
	    	newPlayerDTO.setBoardSize(newBoard.getBoardSize().length);
	    	
	    	newPlayerDTO.setBoardSize(newBoard.getBoardSize().length);
			
			when(service.registerNewPlayer(newPlayerName)).thenReturn(newPlayerDTO);
			
    		mockMvc.perform(
			        post("/rest/api/addPlayer")
			        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			        .content("{\"playerName\":\"John\"}"))			                
				.andExpect(status().isOk());
    		
    		verify(service).registerNewPlayer(newPlayerName);
    }
    
    
}
