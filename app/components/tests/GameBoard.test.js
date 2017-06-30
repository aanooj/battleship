import React from 'react';
import { shallow } from 'enzyme';
import renderer from 'react-test-renderer';
import GamePlot from './../GameBoard';
import { retriveShipLocation } from './../../actions';

describe('GameBoard --- Snapshot', () => {
  const currentUser = {
    message: '',
    status: true,
    statusCode: 200,
    gameId: '',
    playerId: '',
    playerName: '',
  };
  const shipLocation = {};
  const data = {};
  it('capturing Snapshot of GamePlot', () => {
    const renderedValue = renderer.create(<GamePlot
      currentUser={currentUser}
      retriveShipLocation={retriveShipLocation}
      shipLocation={shipLocation}
      data={data}
    />).toJSON();
    expect(renderedValue).toMatchSnapshot();
  });
});
