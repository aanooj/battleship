/* istanbul ignore next */
import { fromJS } from 'immutable';

import {
  ADD_PLAYER,
  SHIP_LOCATION,
  STATUS,
} from './constants';
/* istanbul ignore next */
export const currentUser = (state = fromJS({}), action) => {
  switch (action.type) {
    case ADD_PLAYER: {
      // const currentUser = state.set('currentUser', action.data);
      sessionStorage.setItem('currentUser', JSON.stringify(action.data));
      return state.set('currentUser', action.data);
    }
    default:
      return state;
  }
};
export const shipLocation = (state = fromJS({}), action) => {
  switch (action.type) {
    case SHIP_LOCATION: {
      return state.set('shipLocation', action.data);
    }
    default:
      return state;
  }
};

export const turnStatus = (state = fromJS({}), action) => {
  switch (action.type) {
    case STATUS: {
      return state.set('res', action.data);
    }
    default:
      return state;
  }
};
