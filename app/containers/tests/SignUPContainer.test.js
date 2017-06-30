import React from 'react';
import { shallow } from 'enzyme';
import configureStore from 'redux-mock-store';

import SignUpContainer from './../index';

describe('Sign Up Container --- REACT-REDUX (Shallow + passing the {store} directly)', () => {
  const initialState = {};
  const mockStore = configureStore();
  let store,
    container;

  beforeEach(() => {
    store = mockStore(initialState);
    container = shallow(<SignUpContainer store={store} />);
  });

  it('render the SignUpContainer(SMART) component', () => {
    expect(container.length).toEqual(1);
  });
});
