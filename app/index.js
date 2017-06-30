/**
 * index.js
 *
 * This is the entry file for the application, only setup and boilerplate
 * code.
 */

// Needed for redux-saga es6 generator support
import 'babel-polyfill';

// Import all the third party stuff
import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, browserHistory } from 'react-router';
import { syncHistoryWithStore } from 'react-router-redux';

// Import root app
import App from './core/App';

// Import selector for `syncHistoryWithStore`
import { makeSelectLocationState } from './core/selectors';

import configureStore from './store';

// Import routes
import createRoutes from './routes';

// Import Styles
import './styles/styles.css';
// Create redux store with history
// this uses the singleton browserHistory provided by react-router
// Optionally, this could be changed to leverage a created history
// e.g. `const browserHistory = useRouterHistory(createBrowserHistory)();`
const initialState = {};
const store = configureStore(initialState, browserHistory);

// Sync history and store, as the react-router-redux reducer
// is under the non-default key ("routing"), selectLocationState
// must be provided for resolving how to retrieve the "route" in the state
const history = syncHistoryWithStore(browserHistory, store, {
  selectLocationState: makeSelectLocationState(),
});

// Set up the router, wrapping all Routes in the App component
const rootRoute = {
  component: App,
  childRoutes: createRoutes(store),
};

const render = () => {
  ReactDOM.render(
    <Provider store={store}>
      <Router
        history={history}
        routes={rootRoute}
      />
    </Provider>,
    document.getElementById('app')
  );
};

render();
