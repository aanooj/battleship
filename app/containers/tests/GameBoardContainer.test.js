import React from 'react';
import { shallow } from 'enzyme';
import configureStore from 'redux-mock-store';
import { fromJS } from 'immutable';
import GameBoardContainer from './../GameBoardContainer';

describe('Game Board Container --- REACT-REDUX (Shallow + passing the {store} directly)', () => {
  const initialState = {
    data: '',
    currentUser: { message: '', status: true, statusCode: 200, gameId: '', playerId: '', playerName: '' },
  };
  const mockStore = configureStore();
  let store,
    container;

  beforeEach(() => {
    store = mockStore(fromJS(initialState));
    const data = { message: '', status: true, statusCode: 200, gameId: '', playerId: '', playerName: '' };
    global.sessionStorage.setItem('currentUser', JSON.stringify(data));
    container = shallow(<GameBoardContainer store={store} />);
  });

  it('render the GameBoardContainer(SMART) component', () => {
    expect(container.length).toEqual(1);
  });

  it('check Prop matches with initialState', () => {
    expect(container.prop('currentUser')).toEqual(initialState.currentUser);
  });
});
