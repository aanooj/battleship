import React from 'react';
import { shallow } from 'enzyme';
import renderer from 'react-test-renderer';
import SignUpComponent from '../GameSignUp';
import { addPlayer } from './../../actions';
// Snapshot for SignUpForm React Component
describe('GameSignUp --- Snapshot', () => {
  it('capturing Snapshot of GameSignUp', () => {
    const renderedValue = renderer.create(<SignUpComponent addPlayer={addPlayer} />).toJSON();
    expect(renderedValue).toMatchSnapshot();
  });
});

describe('Game signup test', () => {
  let wrapper;
  let output;
  beforeEach(() => {
        // output = mockStore(initialState);
    wrapper = shallow(<SignUpComponent output={output} />);
  });
  it('+++ render the DUMB component', () => {
    expect(wrapper.length).toEqual(1);
  });
  it('+++ contains with h1 tag and p Tag', () => {
    expect(wrapper.containsAnyMatchingElements([
      <h1>WELCOME TO BATTLESHIP!</h1>,
      <p>Sign up to Start the Game!</p>,
    ])).toEqual(true);
  });
});
