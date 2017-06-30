/**
 * Created by sgumma on 27-06-2017.
 */
import React, { Component } from 'react';
import GamePlayArea from './GamePlayArea';
class GamePlot extends Component { // eslint-disable-line

  render() {
    const { currentUser } = this.props;
    return (
      <div>
        <h3 className="player-title">Start Game!</h3>
        <GamePlayArea currentUser={currentUser} {...this.props} />
      </div>
    );
  }
}
GamePlot.propTypes = {
  currentUser: React.PropTypes.object,
};
export default GamePlot;
