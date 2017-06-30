/**
 *
 * App
 *
 * This component is the skeleton around the actual pages, and should only
 * contain code that should be seen on all pages. (e.g. navigation bar)
 */

import React from 'react';

export function App(props) {
  return (
    <div>
      {React.Children.toArray(props.children)}
    </div>
  );
}

App.propTypes = {
  children: React.PropTypes.node, // eslint-disable-line
};

export default App;
