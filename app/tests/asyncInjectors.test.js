/**
 * Test async injectors
 */

import { memoryHistory } from 'react-router';
import { fromJS } from 'immutable';

import configureStore from '../store';

import {
  getAsyncInjectors,
} from '../asyncInjectors';

// Fixtures

const initialState = fromJS({ reduced: 'soon' });

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case 'TEST':
      return state.set('reduced', action.payload);
    default:
      return state;
  }
};


describe('asyncInjectors', () => {
  let store;

  describe('getAsyncInjectors', () => {
    beforeAll(() => {
      store = configureStore({}, memoryHistory);
    });

    it('given a store, should return all async injectors', () => {
      const { injectReducer } = getAsyncInjectors(store);

      injectReducer('test', reducer);

      const actual = store.getState().get('test');
      const expected = initialState.merge({ reduced: 'soon' });

      expect(actual.toJS()).toEqual(expected.toJS());
    });

    it('should throw if passed invalid store shape', () => {
      let result = false;

      Reflect.deleteProperty(store, 'dispatch');

      try {
        getAsyncInjectors(store);
      } catch (err) {
        result = err.name === 'Invariant Violation';
      }

      expect(result).toBe(true);
    });
  });
});
