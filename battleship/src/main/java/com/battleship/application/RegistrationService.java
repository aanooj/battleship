package com.battleship.application;

import com.battleship.application.dto.RegisterNewPlayerDTO;
import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.GameInitiationException;
import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.handling.NoGameAvailableException;


/**
 * This is the Registration Service for new player.
 *  
 * @author amall3
 *
 */
public interface RegistrationService {

	
	
	/**
	 * This service is to retrieve latest available game for the new player. 
	 * 
	 * @return
	 * @throws GameInitiationException
	 * @throws NoGameAvailableException 
	 */
	public Game retrieveLatestAvailableGame() throws GameInitiationException, NoGameAvailableException;
	
	
	
	/**
	 * This service can be used to register new Player in Game. 
	 * Each player will be assigned with unique game id.  
	 * 
	 * @param <code>Player</code>
	 * @return <code>Boolean</code> Result of the Player creation 
	 * @throws GameInitiationException 
	 * @throws NoGameAvailableException 
	 * @throws InvalidPlayerObject
	 */
	public RegisterNewPlayerDTO registerNewPlayer(String name) throws InvalidPlayerException, GameInitiationException, NoGameAvailableException;
	
}