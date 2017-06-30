package com.battleship.application.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.battleship.application.PrepareGroundService;
import com.battleship.application.util.GenericUtil;
import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.NoBoardAvailableException;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.handling.NoPlayerFoundException;
import com.battleship.domain.model.player.Player;
import com.battleship.domain.model.ship.Ship;
import com.battleship.infrastructure.BattleShipBoardRepository;
import com.battleship.infrastructure.BattleShipGameRepository;

/**
 * This class is an implementation of <code>PrepareGroundService</code> and has
 * methods related to placement of ship and retrieving the ship coordinates.
 * 
 * @author amall3
 *
 */
@Service
@ComponentScan("com.battleship.repository")
public class PrepareGroundServiceImpl implements PrepareGroundService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BattleShipGameRepository gameRepository;
	
	@Autowired
	private BattleShipBoardRepository battleShipBoardRepository;
	
	@Autowired
	private GenericUtil genericUtil;

	/**
	 * This method implements placing ship service.
	 * 
	 * @param gameId
	 * @param playerId
	 * @param shipCoordinates
	 * @throws NoBoardAvailableException 
	 * 
	 * @throws <code>NoPlayerFoundException</code>, <code>NoGameAvailableException</code> 
	 */
	@Override
	public Player setShipCoodinatesForPlayer(String gameId, String playerId, String shipCoordinates) throws NoGameAvailableException, NoPlayerFoundException, NoGameAvailableException, NoBoardAvailableException {

		logger.debug("The Game Id Passed is {0}", gameId);
		logger.debug("The Player Id Passed is {0}", playerId);
		
		Game gameObj = gameRepository.getGameByID(Integer.valueOf(gameId));
		
		List<Player> playerList = gameObj.getGamePlayers();
		
		for (Player player : playerList) {
			if (player.getPlayerID() == Integer.valueOf(playerId)) {
				
				Ship ship = new Ship();
				Board board = battleShipBoardRepository.getBoardByID(player.getBoardId());
				board.setShipPositionCoordinateList(genericUtil.convertToListOfArrayIntegers(shipCoordinates));
				player.setShip(ship);
				return player;
			}
		}
		throw new NoPlayerFoundException();
	}

	/**
	 * This method implements service to retrieve list of players for the game.
	 * 
	 * @param gameId
	 * @return <code>List</code> of Players
	 * 
	 * @throws <code>NoGameAvailableException</code>
	 */
			
	@Override
	public List<Player> getPlayerDetails(String gameId) throws NoGameAvailableException {
		
		logger.debug("The Game Id Passed is {0}", gameId);
		
		Game gameObj = gameRepository.getGameByID(Integer.valueOf(gameId));
		
		return gameObj.getGamePlayers();
	}
}
