/**
 * Created by sgumma on 6/29/17.
 */
import React, { Component } from 'react';

export default class GameoverModal extends Component {
  constructor() {
    super();
    this.startNewGame = this.startNewGame.bind(this);
  }
  startNewGame() {
    this.props.startNew();
  }
  render() {
    return (
      <div style={{ backgroundColor: 'rgba(0, 0, 0, 0.5)', position: 'absolute', width: '100%', height: '100vh', textAlign: 'center', paddingTop: '20%', top: '0', left: '0' }} >
        <div style={{ backgroundColor: '#fff', fontSize: '2.4rem', width: '50%', margin: '0 auto', fontFamily: 'Arial', padding: '5%' }}>
          Game Over!!!
          <br />
          {this.props.winner}
          <br />
          <button className="next-submit" onClick={this.startNewGame}>Start New Game</button>
        </div>
      </div>
    );
  }
}
GameoverModal.propTypes = {
  winner: React.PropTypes.string,
  startNew: React.PropTypes.func,
};
