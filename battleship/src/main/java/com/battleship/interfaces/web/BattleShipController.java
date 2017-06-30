/**
 * 
 */
package com.battleship.interfaces.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.battleship.application.PlayGameService;
import com.battleship.application.PrepareGroundService;
import com.battleship.application.RegistrationService;
import com.battleship.application.dto.HitOpponentShipDTO;
import com.battleship.application.dto.HitOpponentShipUpdateDTO;
import com.battleship.application.dto.RegisterNewPlayerDTO;
import com.battleship.application.dto.TurnStatusDTO;
import com.battleship.domain.model.handling.GameInitiationException;
import com.battleship.domain.model.handling.InvalidPlayerException;
import com.battleship.domain.model.handling.NoBoardAvailableException;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.handling.NoPlayerFoundException;
import com.battleship.domain.model.player.Player;
import com.battleship.interfaces.dto.CheckTurnStatusRequestDTO;
import com.battleship.interfaces.dto.CheckTurnStatusResponseDTO;
import com.battleship.interfaces.dto.HitOpponentShipUpdateRequestDTO;
import com.battleship.interfaces.dto.HitOpponentShipUpdateResponseDTO;
import com.battleship.interfaces.dto.NewPlayerRequestDTO;
import com.battleship.interfaces.dto.PlaceShipRequestDTO;
import com.battleship.interfaces.dto.RegisterNewPlayerResponseDTO;
import com.battleship.interfaces.dto.RetrievePlayerDetailsRequestDTO;
import com.battleship.interfaces.util.InterfacesUtil;

/**
 * This is main controller for all the APIs used in Battleship Game.
 * There are 3 functional contexts as:
 * Registration, Prepare Ground and Play Game
 *  
 *  
 * @author amall3
 *
 */
@RestController
@RequestMapping("/rest/api")
@CrossOrigin
public class BattleShipController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private PrepareGroundService prepareGroundService;
	
	@Autowired
	private PlayGameService playGameService;	
	
	@Autowired
	private InterfacesUtil util;

	/**
	 * This REST API will accept Player name to register in BattleShip Game.
	 * Registered player details will be returned.
	 * 
	 * 
	 * @param <code>NewPlayerRequest</code>
	 * @param <code>Errors</code>
	 * @return <code>Player</code> upon successfully else
	 *         <code>BadRequest</code> response will be returned
	 * 
	 * @throws GameInitiationException
	 * @throws InvalidPlayerException
	 * @throws NoGameAvailableException
	 */
	@RequestMapping(value = "/addPlayer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<RegisterNewPlayerResponseDTO> registerPlayer(@Valid @RequestBody NewPlayerRequestDTO newPlayerRequest, Errors errors) {

		logger.debug("[BattleShipRegistrationController.registerPlayer()] : registerPlayer Service called");
		RegisterNewPlayerResponseDTO registerNewPlayerResponseDTO = new RegisterNewPlayerResponseDTO();
		
		if (errors.hasErrors()) {
			logger.debug("[BattleShipRegistrationController.registerPlayer()] : There are validation errors > " + errors);
			registerNewPlayerResponseDTO.setMessage(util.getValidationErrors(errors).toString());
			return ResponseEntity.badRequest().body(registerNewPlayerResponseDTO);
		}
		
		try {
			RegisterNewPlayerDTO registerNewPlayerDTO = registrationService.registerNewPlayer(newPlayerRequest.getPlayerName());
			if (null != registerNewPlayerDTO)
				BeanUtils.copyProperties(registerNewPlayerResponseDTO, registerNewPlayerDTO);
			return ResponseEntity.ok(registerNewPlayerResponseDTO);

		} catch (GameInitiationException | InvalidPlayerException | NoGameAvailableException | IllegalAccessException | InvocationTargetException e) {
			registerNewPlayerResponseDTO.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(registerNewPlayerResponseDTO);
		}
	}
	
	/**
	 * Once Player places his ship and prepare his battleground, this service can be called to record ship coordinates. 
	 * 
	 * 
	 * @param placeShipRequest
	 * @param errors
	 * @return <code>Player</code> object if successful.
	 *  
	 */
	@RequestMapping(value = "/placeShip", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<?> placeShip(@Valid @RequestBody PlaceShipRequestDTO placeShipRequest, Errors errors) {

		logger.debug("[BattleShipController.placeShip()] : Place Ship for a Player Service called");

		if (errors.hasErrors()) {
			logger.debug("[BattleShipController.placeShip()] : There are validation errors > " + errors);
			return ResponseEntity.badRequest().body(util.getValidationErrors(errors));
		}
	
		logger.debug("[BattleShipController.placeShip()] : The Game Id Passed Is {0}:", placeShipRequest.getGameId());
		logger.debug("[BattleShipController.placeShip()] : The Player Id Passed Is {0}:", placeShipRequest.getPlayerId());
		logger.debug("[BattleShipController.placeShip()] : The Coordinates Passed Is {0}:", placeShipRequest.getShipCoordinates());
		
		try {
			Player updatedPlayer = prepareGroundService.setShipCoodinatesForPlayer(placeShipRequest.getGameId(), placeShipRequest.getPlayerId(), placeShipRequest.getShipCoordinates());
			if (null != updatedPlayer) {
				return ResponseEntity.ok(updatedPlayer);
			} else {
				throw new NoGameAvailableException();
			}
		} catch (NoGameAvailableException | NoPlayerFoundException | NoBoardAvailableException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	/**
	 * Player's ship locations can be retrieved using this service for the requested Game.
	 * 	 * 
	 * @param gameId
	 * @return List of <code>Player</code>(s) with corresponding <code>Ship</code> details
	 * 
	 * 
	 */
	@RequestMapping(value = "/retrievePlayerDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<?> retrievePlayerDetails(@Valid @RequestBody RetrievePlayerDetailsRequestDTO retrievePlayerDetailsRequest, Errors errors) {

		logger.debug("[BattleShipController.retrieveShipLocations()] : Retrieve the Player's ship coordinates .");
		
		if (errors.hasErrors()) {
			logger.debug("[BattleShipController.retrieveShipLocations()] : There are validation errors > " + errors);
			return ResponseEntity.badRequest().body(util.getValidationErrors(errors));
		}
		
		logger.debug("The Game Id Passed Is {0}:", retrievePlayerDetailsRequest.getGameId());
		
		try {
			List<Player> players = prepareGroundService.getPlayerDetails(retrievePlayerDetailsRequest.getGameId());
			if (null != players && !players.isEmpty()){
				return ResponseEntity.ok(players);
			}
		} catch (NoGameAvailableException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/**
	 * Method responsible for returning the turn status for the passed in game
	 * Id.
	 * 
	 * @param gameId
	 * @return
	 */
	@RequestMapping(value = "/checkTurnStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<CheckTurnStatusResponseDTO> checkTurnStatus(@Valid @RequestBody CheckTurnStatusRequestDTO checkTurnStatusRequest, Errors errors) {

		logger.debug("[BattleShipController.checkTurnStatus()] : The Game Id Passed Is {0}:", checkTurnStatusRequest.getGameId());
		logger.debug("[BattleShipController.checkTurnStatus()] : The Player Id Passed Is {0}:", checkTurnStatusRequest.getPlayerId());

		CheckTurnStatusResponseDTO checkTurnStatusResponseDTO = new CheckTurnStatusResponseDTO();
		if (errors.hasErrors()) {
			logger.debug("[BattleShipController.retrieveShipLocations()] : There are validation errors > " + errors);
			checkTurnStatusResponseDTO.setMessage(util.getValidationErrors(errors).toString());
			return ResponseEntity.badRequest().body(checkTurnStatusResponseDTO);
		}

		try {
			TurnStatusDTO turnStatusDTO = playGameService.checkPlayersTurnOrGameOverStatus(
					Integer.parseInt(checkTurnStatusRequest.getGameId()), Integer.parseInt(checkTurnStatusRequest.getPlayerId()));
			
			if(null != turnStatusDTO)
				BeanUtils.copyProperties(checkTurnStatusResponseDTO, turnStatusDTO);
			logger.debug("[BattleShipController.checkTurnStatus()] : Response is :", checkTurnStatusResponseDTO);
			
		} catch (NumberFormatException | NoGameAvailableException | IllegalAccessException | InvocationTargetException | NoBoardAvailableException e) {
			checkTurnStatusResponseDTO.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(checkTurnStatusResponseDTO);
		}
		return ResponseEntity.ok(checkTurnStatusResponseDTO);
	}
	
	
	/**
	 * Method responsible for returning the turn status for the passed in game
	 * Id.
	 * 
	 * @param gameId
	 * @return
	 */
	@RequestMapping(value = "/hitOpponentShip", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<HitOpponentShipUpdateResponseDTO> hitOpponentShipEventUpdate(@Valid @RequestBody HitOpponentShipUpdateRequestDTO hitOpponentShipUpdateRequest, Errors errors) {

		logger.debug("[BattleShipController.hitOpponentShipUpdate()] : The Game Id Passed Is {0}:", hitOpponentShipUpdateRequest.getGameId());
		logger.debug("[BattleShipController.hitOpponentShipUpdate()] : The Player Id Passed Is {0}:", hitOpponentShipUpdateRequest.getPlayerId());
		
		HitOpponentShipUpdateResponseDTO hitOpponentShipUpdateResponseDTO = new HitOpponentShipUpdateResponseDTO();
		
		if (errors.hasErrors()) {
			logger.debug("[BattleShipController.retrieveShipLocations()] : There are validation errors > " + errors);
			hitOpponentShipUpdateResponseDTO.setMessage(util.getValidationErrors(errors).toString());
			return ResponseEntity.badRequest().body(hitOpponentShipUpdateResponseDTO);
		}
		
		try {
			HitOpponentShipUpdateDTO hitOpponentShipUpdateDTO = new HitOpponentShipUpdateDTO();
			BeanUtils.copyProperties(hitOpponentShipUpdateDTO, hitOpponentShipUpdateRequest);
			HitOpponentShipDTO hitOpponentShipDTO = playGameService.hitOpponentShipUpdateEvent(hitOpponentShipUpdateDTO);
			if (null != hitOpponentShipDTO){
				BeanUtils.copyProperties(hitOpponentShipUpdateResponseDTO, hitOpponentShipDTO);
				return ResponseEntity.ok(hitOpponentShipUpdateResponseDTO);
			}
		} catch (NumberFormatException  | IllegalAccessException | InvocationTargetException | NoGameAvailableException | NoBoardAvailableException e) {
			hitOpponentShipUpdateResponseDTO.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(hitOpponentShipUpdateResponseDTO);
		}
		return ResponseEntity.badRequest().body(hitOpponentShipUpdateResponseDTO);
	}
	
}
