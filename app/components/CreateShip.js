import React, { Component } from 'react';
class CreateShip extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: '',
      rows: [0, 1, 2, 3, 4, 5, 6],
      cols: [0, 1, 2, 3, 4, 5, 6],
      shipSelected: false,
      selectable: false,
      selectedRow: null,
      selectedCol: null,
      horizontal: true,
      shipSize: 3,
      selectedCods: [],
    };
  }

  componentWillMount() {
    const { boardSize } = this.props.currentUser;
    const temp = [];
    for (let i = 0; i < boardSize; i++) {
      temp.push(i);
    }
    this.setState({
      rows: temp,
      cols: temp,
    });
  }

  onShipSelect = () => {
    this.setState({
      horizontal: !this.state.horizontal,
    });
  }
  /* highlight cells user put the mouse*/
  mouseOver = (e) => {
    const { horizontal, shipSize, rows, cols } = this.state;
    const val = (e.target.id).split('');
    this.setState({
      selectedRow: parseInt(val[0], 10),
      selectedCol: parseInt(val[1], 10),
    });
    if (horizontal) {
      if (parseInt(val[1], 10) <= (cols.length - shipSize)) {
        this.setState({ selectable: true });
      } else {
        this.setState({ selectable: false });
      }
    } else if (parseInt(val[0], 10) <= (rows.length - shipSize)) {
      this.setState({ selectable: true });
    } else {
      this.setState({ selectable: false });
    }
  };


  /*
   * Handle Cell Click
   * Set the co-oridnates to state / browser session
   */
  handleClick = (e) => {
    const val = parseInt(e.target.id, 10);
    const cods = [];
    const { horizontal, selectable, shipSize } = this.state;

    if (horizontal && selectable) {
      for (let i = 0; i < shipSize; i += 1) {
        cods.push((val + i) > 9 ? (val + i) : (`0${val + i}`));
      }
    } else if (selectable) {
      for (let i = 0; i < shipSize; i += 1) {
        cods.push((val + (i * 10)) > 9 ? (val + (i * 10)) : (`0${val}`));
      }
    }
    console.log(cods);
    this.setState({
      selectedCods: cods,
      shipSelected: true,
    });
    sessionStorage.setItem('cords', cods);
  }

  /*
   * Handle Next  Button Click
   * Send the Co-ordinates to Micro Service
   */
  handleNextClick = () => {
    const { currentUser, placeShip } = this.props;
    const { selectedCods } = this.state;
    console.log(selectedCods);
    placeShip(currentUser, selectedCods);
  }
  /*
   * Vertical Ship Placement
   */
  verticalSelection = (row) => {
    const { selectable, selectedRow, shipSize } = this.state;
    if (selectable && ((row >= selectedRow) && (row <= (selectedRow + (shipSize - 1))))) {
      return 'shipSelected';
    } else if ((selectedRow <= row) && !selectable) {
      return 'shipUnselected';
    }
    return '';
  }
  /*
   * Horizontal Ship Placement
   */
  horizontalSelection = (col) => {
    const { selectable, selectedCol, shipSize } = this.state;
    if (selectable && ((col >= selectedCol) && (col <= (selectedCol + (shipSize - 1))))) {
      return 'shipSelected';
    } else if ((selectedCol <= col) && !selectable) {
      return 'shipUnselected';
    }
    return '';
  }
  /*
   * Create Cells
   */
  cellDivs = (row) => {
    const { selectedRow, horizontal, selectedCol, shipSelected, shipSize, selectedCods, cols } = this.state;
    let selectedTabs = '';

    if (row === selectedRow && horizontal && !shipSelected) {
      return (
        cols.map((col) => {
          selectedTabs = this.horizontalSelection(col);
          return (
            <div
              onFocus={() => undefined}
              role="presentation"
              className={`indent ${selectedTabs}`}
              id={`${row}${col}`}
              onMouseOver={this.mouseOver}
              key={`${row}${col}`}
              onClick={this.handleClick}
            />
          );
        })
      );
    } else if (!horizontal && !shipSelected) {
      return (
        cols.map((col) => {
          if (selectedCol === col) {
            selectedTabs = this.verticalSelection(row);
          } else {
            selectedTabs = '';
          }
          return (
            <div
              onFocus={() => undefined}
              role="presentation"
              className={`indent ${selectedTabs}`}
              id={`${row}${col}`}
              onMouseOver={this.mouseOver}
              key={`${row}${col}`}
              onClick={this.handleClick}
            />
          );
        })
      );
    }
    return (
      cols.map((col) => {
        const cord = `${row}${col}`;
        let tabs = '';
        for (let i = 0; i < shipSize; i += 1) {
          if (cord == selectedCods[i]) { // eslint-disable-line eqeqeq
            tabs = 'shipSelected';
          }
        }

        return (
          <div
            onFocus={() => undefined}
            role="presentation"
            className={`indent ${tabs}`}
            id={`${row}${col}`}
            onMouseOver={this.mouseOver}
            key={`${row}${col}`}
            onClick={this.handleClick}
          />
        );
      })
    );
  }


  render() {
    const { rows, shipSelected, horizontal } = this.state;
    const tabSelection = horizontal ? 'Horizontal' : 'Vertical';
    const shipColor = shipSelected ? 'shipSelected' : 'shipUnselected';
    return (
      <div className="App">
        <div style={{ textAlign: 'center' }}>
          {
            rows.map((row) => (
              <div className={'row'} key={row}>
                {this.cellDivs(row)}
              </div>
            ))
          }
          <button className="next-submit" onClick={this.handleNextClick}> Next</button>
          <div role="presentation" className={`ship ${shipColor}`} onClick={this.onShipSelect}>{tabSelection}</div>
        </div>
      </div>
    );
  }
}
export default CreateShip;

CreateShip.propTypes = {
  placeShip: React.PropTypes.func, // eslint-disable-line
};
