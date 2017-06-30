/*
 * HomeConstants
 * Each action has a corresponding type, which the reducer knows and picks up on.
 * To avoid weird typos between the reducer and the actions, we save them as
 * constants here. We prefix them with 'yourproject/YourComponent' so we avoid
 * reducers accidentally picking up actions they shouldn't.
 *
 * Follow this format:
 * export const YOUR_ACTION_CONSTANT = 'yourproject/YourContainer/YOUR_ACTION_CONSTANT';
 */

export const ADD_PLAYER = 'battleGame/USER/ADD_PLAYER';
export const SHIP_COORDS = 'battleGame/USER/SHIP_COORDS';
export const SHIP_LOCATION = 'battleGame/USER/SHIP_LOCATION';
export const STATUS = 'battleGame/USER/STATUS';

export const ADD_NEW_USER_URI = 'http://10.150.188.108:8180/battleship/rest/api/addPlayer';
export const PLACE_SHIP_URI = 'http://10.150.188.108:8180/battleship/rest/api/placeShip';
export const RETRIVE_SHIP_LOC_URI = 'http://10.150.188.108:8180/battleship/rest/api/retrievePlayerDetails/';
export const CHECK_TURN_URI = 'http://10.150.188.108:8180/battleship/rest/api/checkTurnStatus';
export const HIT_OPP_SHIP = 'http://10.150.188.108:8180/battleship/rest/api/hitOpponentShip';
