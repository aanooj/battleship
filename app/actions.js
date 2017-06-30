import request from 'axios';
import {
  ADD_PLAYER,
  SHIP_COORDS,
  SHIP_LOCATION,
  STATUS,
    ADD_NEW_USER_URI,
    PLACE_SHIP_URI,
    RETRIVE_SHIP_LOC_URI,
    CHECK_TURN_URI,
 } from './constants';
import { browserHistory } from 'react-router';

export const addPlayerAPI = (playerName) => { // eslint-disable-line
  console.log(playerName);
  const data = {
    playerName,
  };
  return request({
    method: 'POST',
    url: ADD_NEW_USER_URI,
    data,
  });
};

export const retrieveShipLocationsAPI = (gameId) => { // eslint-disable-line
  const data = {
    gameId,
  };
  return request({
    method: 'POST',
    url: RETRIVE_SHIP_LOC_URI,
    data,
  });
};

export const placeShipAPI = (playerData, cords) => { // eslint-disable-line
  console.log(playerData);
  const data = {
    shipCoordinates: cords.toString(),
    gameId: playerData.gameID,
    playerId: playerData.playerID,
    playerName: playerData.playerName,
  };
  return request({
    method: 'POST',
    url: PLACE_SHIP_URI,
    data,
  });
};

export const checkTurnStatusAPI = (gamedata) => {
  console.log(gamedata);
  return request({
    method: 'POST',
    url: CHECK_TURN_URI,
    data: gamedata,
  });
};

export const setCurrentUser = (data) => ({
  type: ADD_PLAYER,
  data,
});

export const setShipCoordinates = (data) => { // eslint-disable-line
  return {
    type: SHIP_COORDS,
    data,
  };
};

export const setShipLocation = (data) => { // eslint-disable-line
  return {
    type: SHIP_LOCATION,
    data,
  };
};

export const setTurnStatus = (data) => { // eslint-disable-line
  return {
    type: STATUS,
    data,
  };
};

/*
* Function addPlayer
* @param : playerName
*/
export const addPlayer = (playerName) => { //eslint-disable-line
  return function (dispatch) {
    addPlayerAPI(playerName).then((resp) => { // eslint-disable-line
      console.log(resp);
      dispatch(setCurrentUser(resp.data));
      window.location.href = '/placeShip';
    }).catch((err) => {
      Error(err);
    });
  };
};
/*
* Function placeShip
* @param : playerData : Player Information
* @param : Cords : Ship Co-ordinates
*/
export const placeShip = (playerData, cords) => { // eslint-disable-line
  console.log(playerData, cords);
  return function () {
    placeShipAPI(playerData, cords).then((resp) => { // eslint-disable-line
      window.location.href = '/gameBoard';
    }).catch((err) => {
      Error(err);
    });
  };
};
/*
* Function retriveShipLocation
* @param : gameId
*/
export const retriveShipLocation = (gameId) => {// eslint-disable-line
  console.log(gameId);
  return function (dispatch) {
    retrieveShipLocationsAPI(gameId).then((response) => {
      dispatch(setShipLocation(response.data));
      return response.data;
    }).catch((err) => {
      console.log('Error', err);
    });
  };
};
/*
* Function checkTurnStatus
* @param : data
*/
export const checkMyTurnStatus = (data) => { // eslint-disable-line

  return checkTurnStatusAPI(data).then((response) => response.data).catch((err) => {
    console.log('Error', err);
  });
};

/* Start New Game*/
export const startNew = () => {
  sessionStorage.clear();
  browserHistory.push('/');
};
