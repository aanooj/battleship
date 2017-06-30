/**
 * Created by sgumma on 6/29/17.
 */
import React, { Component, PropTypes } from 'react';

export default class GameTable extends Component {
  constructor() {
    super();
    this.handleClick = this.handleClick.bind(this);
    this.cellDivs = this.cellDivs.bind(this);
  }

  handleClick(e) {
    this.props.handleClick(e);
  }

  cellDivs(row, idx) {
    const { cols, tabClass, enablePlayerII } = this.props;
    return (
      cols.map((col) => {
        const playerII = enablePlayerII ?
          (<div
            role="presentation"
            className={`indent ${tabClass} `}
            id={`${row}${col}`}
            onClick={this.handleClick}
            key={`${row}${col}`}
          />)
          : (<div
            className="indent gray "
            id={`${row}${col}`}
            key={`${row}${col}`}
          />);

        const element = (idx === 'l') ? playerII
          : <div className="indent" id={`${row}${col}-${idx}`} key={`${row}${col}`} />;

        return element;
      })
    );
  }

  render() {
    const { rows, keyMap } = this.props;
    return (
      <div>
        {
          rows.map((row) => (
            <div>
              <div className="row" key={row}>
                {this.cellDivs(row, keyMap)}
              </div>

            </div>
          ))
        }
      </div>
    );
  }
}
GameTable.propTypes = {
  cols: PropTypes.array,
  tabClass: PropTypes.string,
  enablePlayerII: PropTypes.bool,
  rows: PropTypes.array,
  keyMap: PropTypes.string,
  handleClick: PropTypes.func,
};
