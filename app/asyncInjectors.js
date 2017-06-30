import { conformsTo, isEmpty, isFunction, isObject, isString } from 'lodash';
import invariant from 'invariant';
import createReducer from './combineReducers';


/**
 * checkStore(store)
 * Validate the shape of redux store
 * @param store
 */
export function checkStore(store) {
  const shape = {
    dispatch: isFunction,
    subscribe: isFunction,
    getState: isFunction,
    replaceReducer: isFunction,
    asyncReducers: isObject,
  };
  invariant(
    conformsTo(store, shape),
    '(app/utils...) asyncInjectors: Expected a valid redux store'
  );
}

/**
 * injectAsyncReducer
 * Inject an asynchronously loaded reducer
 * @param store
 * @param isValid
 * @returns {injectReducer}
 */
export function injectAsyncReducer(store, isValid) {
  return function injectReducer(name, asyncReducer) {
    if (!isValid) checkStore(store);

    invariant(
      isString(name) && !isEmpty(name) && isFunction(asyncReducer),
      '(app/utils...) injectAsyncReducer: Expected `asyncReducer` to be a reducer function'
    );

    if (Reflect.has(store.asyncReducers, name)) return;

    store.asyncReducers[name] = asyncReducer; // eslint-disable-line
    store.replaceReducer(createReducer(store.asyncReducers));
  };
}


/**
 * Helper for creating injectors
 * @param store
 * @returns {{injectReducer: injectReducer}}
 */
export function getAsyncInjectors(store) {
  checkStore(store);

  return {
    injectReducer: injectAsyncReducer(store, true),
  };
}
