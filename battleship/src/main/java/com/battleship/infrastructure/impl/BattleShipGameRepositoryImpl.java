package com.battleship.infrastructure.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.player.Player;
import com.battleship.infrastructure.BattleShipGameRepository;

/**
 * This repository manages <code>Game</code> instances.
 * This repository can be replaced JPA/Database Implementation.
 * 
 * 
 * @author pmalsh
 *
 */
@Component
public class BattleShipGameRepositoryImpl implements BattleShipGameRepository {

	/**
	 * This is LinkedList repository for easy retrieval latest available game and other functions.
	 * 
	 */
	private LinkedList<Game> allGames;
	
	
	//Changing this constructor from private to public as Mockito is unable to call this default constructor while creating mock object.
	//Only for testing purpose until solution available.
	public BattleShipGameRepositoryImpl(){
		allGames = new LinkedList<Game>();	
	}
	

	/**
	 * This implements retrieval of latest available game service.
	 * 
	 * @return Game ID 
	 */
	
	public int latestAvailableGame(){
		
		if(allGames.isEmpty() || allGames.getFirst().getGamePlayers().size() == 2){
			allGames.push(new Game());
			return allGames.getFirst().getGameID();
		}
		else{	
			return allGames.getFirst().getGameID();
		}
	}
	
	/**
	 * This implements retrieval of <code>Game</code> by gameId.
	 * 
	 * @return <code>Game<code>
	 */
	public Game getGameByID(int gameID) throws NoGameAvailableException {
		
		for (Game game : allGames) {
			if(game.getGameID() == gameID){
				return game;
			}
		}
		throw new NoGameAvailableException();
	}
	
	/**
	 * This implements service to add players to the game if that game has less than 2 players.
	 *  
	 * @param player
	 * @param gameID
	 * @return true if player added successfully, false if fails to add player into game
	 * @throws NoGameAvailableException 
	 */
	public boolean addNewPlayerByGameID(Player player, int gameID) throws NoGameAvailableException{
		
		List<Player> playersList = this.getGameByID(gameID).getGamePlayers();
		
		if(playersList.size() < 2){
			playersList.add(player);
			return true;
		}
		return false;
	}
	
}
