import React, { PropTypes } from 'react';
import SingupForm from './SignupForm';

const SignUpComponent = (props) => { // eslint-disable-line arrow-body-style
  // console.log(props)
  return (
    <div>
      <div>
        <h1 className="alignCenter">WELCOME TO BATTLESHIP!</h1>
        <p className="alignCenter">Sign up to Start the Game!</p>
        <SingupForm addPlayer={props.addPlayer} />
      </div>
    </div>
  );
};
SignUpComponent.defaultProps = {
  addPlayer: function addPlayer() {
    return {};
  },
};
SignUpComponent.propTypes = {
  addPlayer: PropTypes.func,
};

export default SignUpComponent;
