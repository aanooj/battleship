import React, { Component, PropTypes } from 'react';

class SignupForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      playerName: '',
      status: false,
    };
  }

  addUserName = (e) => {
    const val = e.target.value;
    this.setState({
      playerName: val,
    });
  }
  handleSubmit = () => {
    const { playerName } = this.state;
    const { addPlayer } = this.props;
    addPlayer(playerName);
  }

  render() {
    return (
      <div className="App">
        <div className="user-box clearboth">
          <div className="user-one float-left">
            <div className="player-title">Enter your Details</div>
            <div>
              <input id="playerName" onChange={this.addUserName} type="text" placeholder="Please Enter name" />
            </div>
            <button onClick={this.handleSubmit}>submit</button>
          </div>
        </div>
      </div>
    );
  }
}
SignupForm.defaultProps = {
  addPlayer: function addPlayer() {
    return {};
  },
};

SignupForm.propTypes = {
  addPlayer: PropTypes.func,
};

export default SignupForm;
