package com.battleship.application.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.battleship.application.RegistrationService;
import com.battleship.application.dto.RegisterNewPlayerDTO;
import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.GameInitiationException;
import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.player.Player;
import com.battleship.infrastructure.BattleShipBoardRepository;
import com.battleship.infrastructure.BattleShipGameRepository;


/**
 * This class implements <code>RegistrationService</code>.
 * 
 * @author amall3
 *
 */

@Service
@ComponentScan("com.battleship.repository")
public class RegistrationServiceImpl implements RegistrationService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BattleShipGameRepository gameRepository;
	
	@Autowired
	private BattleShipBoardRepository battleShipBoardRepository;
	
	@Override
	public Game retrieveLatestAvailableGame() throws GameInitiationException, NoGameAvailableException {
		
		int lstestAvailableGameID = gameRepository.latestAvailableGame();
		
		logger.info("[RegistrationServiceImpl.retrieveLatestAvailableGame] : Latest Available Game ID > "+ lstestAvailableGameID);
		
		return gameRepository.getGameByID(lstestAvailableGameID);
	}
	
	
	
	@Override
	public RegisterNewPlayerDTO registerNewPlayer(String lPlayerName) throws InvalidPlayerException, GameInitiationException, NoGameAvailableException {
		
		logger.debug("[RegistrationServiceImpl.registerNewPlayer] : New Player registration with name > "+lPlayerName);
		RegisterNewPlayerDTO registerNewPlayerDTO = new RegisterNewPlayerDTO();
		boolean playerAddedSuccessfully = false;
		Player newPlayer;
		
		synchronized (this) {
			Game latestAvailableGame = this.retrieveLatestAvailableGame();
			
			newPlayer = new Player(lPlayerName);
			Board board = battleShipBoardRepository.getNewBoard();
			newPlayer.setBoardId(board.getBoardID());
			newPlayer.setGameID(latestAvailableGame.getGameID());
			
			registerNewPlayerDTO.setPlayerID(newPlayer.getPlayerID());
			registerNewPlayerDTO.setBoardId(newPlayer.getBoardId());
			registerNewPlayerDTO.setGameID(newPlayer.getGameID());
			registerNewPlayerDTO.setPlayerName(newPlayer.getPlayerName());
			registerNewPlayerDTO.setBoardSize(board.getBoardSize().length);
			playerAddedSuccessfully = gameRepository.addNewPlayerByGameID(newPlayer, latestAvailableGame.getGameID());
		}
		
		if(playerAddedSuccessfully){
			logger.debug("[RegistrationServiceImpl.registerNewPlayer] : New Player registration done !! with name > "+lPlayerName);
			return registerNewPlayerDTO;
		}
		throw new NoGameAvailableException();
	}


	

}
