import {
  ADD_PLAYER,
  SHIP_COORDS,
  SHIP_LOCATION,
  STATUS,
} from '../constants';

import {
  addPlayer,
  setCurrentUser,
  addPlayerAPI,
  retrieveShipLocationsAPI,
  placeShipAPI,
  checkTurnStatusAPI,
  setShipCoordinates,
  setShipLocation,
  setHitorTurnStatus,
  placeShip,
  retriveShipLocation,
    checkMyTurnStatus,
    setTurnStatus
} from '../actions';

describe('Default Actions', () => {
  describe('Add New Player', () => {
    it('should return function', () => {
      const userInput = 'Max';
      const result = addPlayer(userInput);
      expect(result).toBeDefined();
    });
  });
  describe('Add New Player API', () => {
    it('should return ajax call', () => {
      const url = '';
      const playerName = 'Test';
      const result = addPlayerAPI(url, playerName);
      expect(result).toBeDefined();
    });
  });
  it('should return the correct type and the passed name', () => {
    const fixture = {
      message: '',
      status: true,
      statusCode: 200,
      gameId: '',
      playerId: '',
      playerName: '',
    };
    const expectedResult = {
      type: ADD_PLAYER,
      data: fixture,
    };

    expect(setCurrentUser(fixture)).toEqual(expectedResult);
  });
  describe('Retriving Ship location API', () => {
    it('should return a function', () => {
      const url = '',
        gameId = '';
      const result = retrieveShipLocationsAPI(url, gameId);
      expect(result).toBeDefined();
    });
  });
  describe('Place Ship location API', () => {
    it('should return a function', () => {
      const data = {
        shipCoordinates: '',
        gameId: '',
        playerId: '',
        playerName: '',
      };
      const url = '';
      const result = placeShipAPI(url, data);
      expect(result).toBeDefined();
    });
  });
  describe('Check turn status API', () => {
    it('should return a function', () => {
      const data = '';
      const url = '';
      const result = checkTurnStatusAPI(url, data);
      expect(result).toBeDefined();
    });
  });
  describe('to set the Ship Coordinates', () => {
    it('should return the correct type and the passed data', () => {
      const data = '';
      const expectedResult = {
        type: SHIP_COORDS,
        data,
      };

      expect(setShipCoordinates(data)).toEqual(expectedResult);
    });
  });
  describe('To set the Ship Location', () => {
    it('should return the correct type and the passed data', () => {
      const data = '';
      const expectedResult = {
        type: SHIP_LOCATION,
        data,
      };

      expect(setShipLocation(data)).toEqual(expectedResult);
    });
  });
  describe('To get the Turn Status of the Hit player', () => {
    it('should return the correct type and the passed data', () => {
      const data = '';
      const expectedResult = {
        type: STATUS,
        data,
      };

      expect(setTurnStatus(data)).toEqual(expectedResult);
    });
  });
  describe('To Place a Ship', () => {
    it('should return function', () => {
      const playerData = '';
      const cords = [];
      const result = placeShip(playerData, cords);
      expect(result).toBeDefined();
    });
  });
  describe('To Retrive Ship Location', () => {
    it('should return function', () => {
      const gameId = '';
      const result = retriveShipLocation(gameId);
      expect(result).toBeDefined();
    });
  });
  describe('To Check Turn Status', () => {
    it('should return function', () => {
      const data = {
        gameId: '',
        playerId: '',
        playerName: '',
        hitOrMiss: true,
        gameOver: false,
        checkTurnStatusOnly: false,
        hitCoordinate: '',
      };
      const result = checkMyTurnStatus(data);
      expect(result).toBeDefined();
    });
  });
});
