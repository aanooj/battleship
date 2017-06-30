import React from 'react';
import { shallow, mount } from 'enzyme';
import renderer from 'react-test-renderer';
import { fromJS } from 'immutable';

import * as reducers from './../reducer';
import {
  ADD_PLAYER,
  SHIP_LOCATION,
  STATUS,
} from './../constants';

describe('R E D U C E R --- Test shipLocation', () => {
  it('reducer for SHIP_LOCATION', () => {
    let state = {};
    const data = fromJS({ message: '', status: false, statusCode: 200, gameId: '', players: null, proceedGame: false });
    state = reducers.shipLocation(fromJS(state), { type: SHIP_LOCATION, data });
    expect(state.get('shipLocation')).toEqual(data);
  });
  it(' reducer for default actions', () => {
    let state = {};
    state = reducers.shipLocation(fromJS(state), { type: '', data: {} });
    expect(state).toEqual(fromJS({}));
  });
});
describe('R E D U C E R --- Test turnStatus', () => {
  it(' reducer for STATUS', () => {
    let state = {};
    const data = fromJS({ message: '', status: false, statusCode: 200, gameId: '', players: null, proceedGame: false });
    state = reducers.turnStatus(fromJS(state), { type: STATUS, data });
    expect(state.get('res')).toEqual(data);
  });
  it(' reducer for default actions', () => {
    let state = {};
    state = reducers.turnStatus(fromJS(state), { type: '', data: {} });
    expect(state).toEqual(fromJS({}));
  });
});

describe('R E D U C E R --- Test currentUser', () => {
  it(' reducer for ADD_PLAYER', () => {
    let state = {};
    const data = fromJS({ message: '', status: false, statusCode: 200, gameId: '', players: null, proceedGame: false });
    state = reducers.currentUser(fromJS(state), { type: ADD_PLAYER, data });
    expect(state.get('currentUser')).toEqual(data);
  });
  it(' reducer for default actions', () => {
    let state = {};
    state = reducers.currentUser(fromJS(state), { type: '', data: {} });
    expect(state).toEqual(fromJS({}));
  });
});
//* *************************`*****************************************************************************`
