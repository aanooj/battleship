import React from 'react';
import { shallow } from 'enzyme';
import configureStore from 'redux-mock-store';

import PlaceShipContainer from './../PlaceShipContainer';

describe('Place Ship Container --- REACT-REDUX (Shallow + passing the {store} directly)', () => {
  const initialState = {
    currentUser: { message: '', status: true, statusCode: 200, gameId: '', playerId: '', playerName: '' },
    data: '',
    rows: [0, 1, 2, 3, 4, 5, 6],
    cols: [0, 1, 2, 3, 4, 5, 6],
    shipSize: 3,
  };

  const mockStore = configureStore();
  let store,
    container;

  beforeEach(() => {
    store = mockStore(initialState);
    const data = { message: '', status: true, statusCode: 200, gameId: '', playerId: '', playerName: '' };
    global.sessionStorage.setItem('currentUser', JSON.stringify(data));
    container = shallow(<PlaceShipContainer store={store} />);
  });


  it('render the PlaceShipContainer(SMART) component', () => {
    expect(container.length).toEqual(1);
  });

  it('check Prop matches with initialState', () => {
    expect(container.prop('currentUser')).toEqual(initialState.currentUser);
    expect(container.prop('data')).toEqual(initialState.data);
    expect(container.prop('rows')).toEqual(initialState.rows);
    expect(container.prop('cols')).toEqual(initialState.cols);
    expect(container.prop('shipSize')).toEqual(initialState.shipSize);
  });
});
