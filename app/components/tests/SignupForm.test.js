import React from 'react';
import { shallow } from 'enzyme';
import renderer from 'react-test-renderer';

import SignupForm from './../SignupForm';
import { addPlayer } from './../../actions';
// Snapshot for SignUpForm React Component
describe('Sign UP Form --- Snapshot', () => {
  it('capturing Snapshot of SignUPForm', () => {
    const renderedValue = renderer.create(<SignupForm addPlayer={addPlayer} />).toJSON();
    expect(renderedValue).toMatchSnapshot();
  });
});

//* ******************************************************************************************************
describe('Sign Up Form --- Shallow Render REACT COMPONENTS', () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallow(<SignupForm addPlayer={addPlayer} />);
  });

  it('should render the DUMB component', () => {
    expect(wrapper.length).toEqual(1);
  });
  it('should contains button ', () => {
    expect(wrapper.find('button').type()).toEqual('button');
  });
  it('should contains input ', () => {
    expect(wrapper.find('input').type()).toEqual('input');
  });
  it('should call addPlayer on click of input value', () => {
    wrapper.addPlayer = jest.fn();
    wrapper.find('button').simulate('click');
    wrapper.addPlayer(); // check this again
    expect(wrapper.addPlayer).toHaveBeenCalled();
  });
  it('should call addPlayer on Change of input value', () => {
    wrapper.find('input').simulate('change', { target: {
      value: 'Player1',
    } });
    expect(wrapper.state('playerName')).toEqual('Player1');
  });
});
