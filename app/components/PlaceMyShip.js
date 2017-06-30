import React, { Component } from 'react';
// import ship cell table
import CreateShip from './CreateShip';

export default class PlaceMyShip extends Component { // eslint-disable-line react/prefer-stateless-function

  render() {
    const currentUser = JSON.parse(window.sessionStorage.getItem('currentUser'));
    return (
      <div>
        <div style={{ textAlign: 'center', padding: '20px' }}>Welcome {currentUser.playerName}!!!</div>
        <CreateShip placeShip={this.props.placeShip} currentUser={currentUser} />
      </div>
    );
  }

}

PlaceMyShip.propTypes = {
  placeShip: React.PropTypes.func,
};

