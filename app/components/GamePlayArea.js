import React, { Component } from 'react';
import axios from 'axios';
import GameoverModal from './GameOverModal';
import GameTable from './GameTable';


class GamePlayArea extends Component {
  constructor(props) {
    super(props);
    // set default state
    this.state = {
      data: '',
      rows: [0, 1, 2, 3, 4, 5, 6],
      cols: [0, 1, 2, 3, 4, 5, 6],
      shipCods: [],
      shipSize: 3,
      hitCods: [],
      missCods: [],
      hitMe: false,
      tabClass: '',
      enablePlayerII: false,
      myTurn: false,
      currentPlayer: null,
      userturn: 'waiting for opponent',
      gameStarted: false,
    };
    this.retriveShipData = this.retriveShipData.bind(this);
    this.updateMyHitStatus = this.updateMyHitStatus.bind(this);
    this.startGame = this.startGame.bind(this);
    this.postShipHit = this.postShipHit.bind(this);
    this.checkTurnAsync = this.checkTurnAsync.bind(this);
  }

  componentWillMount() {
    /* Retrive Oponent Ship Information on Component Load*/
    this.retriveShipData();
  }

  componentWillReceiveProps(nextProps) {
    const { shipLocation } = nextProps.data.shipLocation;
    if (shipLocation.length === 2) {
      this.setState({
        response: shipLocation,
        gameStarted: true,
      });
      this.startGame(shipLocation);
    }
  }

  shouldComponentUpdate(nextProps, nextState) {
    const { shipLocation } = nextProps.data.shipLocation;
    if (shipLocation.length < 2) {
      this.retriveShipData();
    }
    return true;
  }

  /*
   * Get Current Player Name
   * @param response:  response from retrive ship data
   * @param name:  current player name
   */
  getCurrentPlayer(response, id) {
    return response.filter((player) => player.playerID === id);
  }

  /*
   * Get Oponent Player Name
   * @param response:  response from retrive ship data
   * @param name:  current player name
   */
  getOponent(response, id) {
    return response.filter((player) => player.playerID !== id);
  }

  startGame(res) {
    const { currentUser } = this.props;
    const currentPlayer = this.getCurrentPlayer(res, currentUser.playerID);
    const oponent = this.getOponent(res, currentUser.playerID);
    const myTurn = currentPlayer[0].playerID > oponent[0].playerID;
    if (oponent[0].ship != null) {
      this.setState({
        enablePlayerII: true,
        oponent: oponent[0],
        myTurn,
        userturn: myTurn ? 'your turn' : 'oponent turn',
      });
      if (!myTurn) {
        this.retriveTurnStatus();
      }
    } else {
      this.retriveShipData();
    }
  }

  /* Retrive Ship Data */
  retriveShipData() {
    const { currentUser, retriveShipLocation } = this.props;
    retriveShipLocation(currentUser.gameID);
  }


  /**
   * Retrive Turn Staus
   * @param cellId: cell id of user triggered cell -  from state
   * @param hitOrMiss : check if the ship has been hit or not- from state
   */

  retriveTurnStatus() {
    const { currentUser } = this.props;
    const data = {
      gameId: currentUser.gameID,
      playerId: currentUser.playerID,
    };
    this.checkTurnAsync(data);
  }


  /*
   * Post Hit Status
   * Asnc call data after user hits the cell,
   * pass data to ajax call of cellId, hitorMiss, and Game status
   **/

  postHitStatus(cellId, gameOver) {
    const { currentUser } = this.props;
    const data = {
      gameId: currentUser.gameID,
      playerId: currentUser.playerID,
      gameOver,
      hitCoordinate: cellId,
    };
    this.postShipHit(data, gameOver);
  }

  postShipHit(data) {
    axios.post('http://10.150.188.108:8180/battleship/rest/api/hitOpponentShip', data).then((res) => {
      console.log(res.data.opponentHitCoordinates);
      const oponentHitcords = res.data.opponentHitCoordinates;
      const opoHitcords = oponentHitcords !== '' ? oponentHitcords.split(',') : [];
      const oponentMisscords = res.data.opponentMissCoordinates;
      const opoMisscords = oponentMisscords != '' ? oponentMisscords.split(',') : [];

      if (opoHitcords.length) {
        opoHitcords.map((cord, i) => {
          console.log(cord);
          document.getElementById(cord).className += 'indent hitClass';
        });
      }
      if (opoMisscords.length) {
        opoMisscords.map((cord, i) => {
          console.log(cord);
          document.getElementById(cord).className += 'indent missClass';
        });
      }
      this.setState({
        myTurn: false,
        gameOver: res.data.gameOver,
      });
      if (!res.data.gameOver) {
        this.retriveTurnStatus();
      }
    });
  }

  /*
   * Async call to check turn status
   * @param data : object set from retriveTurnStatus/updateMyHitStatus
   */
  checkTurnAsync(data) {
    axios.post('http://10.150.188.108:8180/battleship/rest/api/checkTurnStatus', data).then((res) => {
      this.setState({
        myTurn: res.data.turnStatus,
        userturn: res.data.turnStatus ? 'your turn' : 'opponent turn',
        gameOver: res.data.gameOver,
      });
      if (!res.data.turnStatus && !res.data.gameOver) {
        setTimeout(() => {
          this.retriveTurnStatus();
        }, 500);
      } else if (res.data.turnStatus && !res.data.gameOver) {
        this.updateMyHitStatus(res.data);
      }
    });
  }

  /* Update my ship hit status*/
  updateMyHitStatus(res) {
    let myHitcords = res.myHitCoordinates;
    myHitcords = myHitcords !== '' ? myHitcords.split(',') : [];

    let myMisscords = res.myMissCoordinates;
    myMisscords = myMisscords !== '' ? myMisscords.split(',') : [];

    if (myHitcords.length) {
      myHitcords.map((cord) => {
        console.log('hit', cord);
        document.getElementById(`${cord}-r`).className = 'indent hitClass';
      });
    }
    if (myMisscords.length) {
      console.log('myMisscords', myMisscords);
      myMisscords.map((cord) => {
        console.log('miss', cord);
        document.getElementById(`${cord}-r`).className = 'indent missClass';
      });
    }
    if (res.gameOver) {
      this.setState({
        myTurn: true,
      });
    }
  }


  /* handle cell Click */
  handleClick = (e) => {
    // load data from state
    const target = e.target;
    if (!this.state.myTurn) return false;
    this.postHitStatus(target.id);
  }


  render() {
    const { gameOver, oponent, userturn, winner } = this.state;
    const currentPlayer = 'My';
    const oponentPlayer = (typeof oponent !== 'undefined' && oponent !== null) ? oponent.playerName : 'Waiting for Oponent';
    return (
      <div>
        {!gameOver && <div className="bubble">{userturn}</div>}
        <div className="battle-players">
          {gameOver && <GameoverModal winner={winner} {...this.props} />}
          <div className="second-player float-left">
            <div style={{ textAlign: 'center' }}>{currentPlayer} Ship</div>
            <GameTable {...this.state} keyMap="r" handleClick={this.handleClick} />
          </div>
          <div className="third-class float-left">
            <div style={{ textAlign: 'center' }} id="curp">{oponentPlayer} Ship</div>
            <GameTable {...this.state} keyMap="l" handleClick={this.handleClick} />
          </div>
        </div>
      </div>
    );
  }
}

export default GamePlayArea;
