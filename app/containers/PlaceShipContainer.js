/**
 * Created by sgumma on 27-06-2017.
 */

import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as actions from '../actions';

import PlaceMyShip from '../components/PlaceMyShip';

const mapStateToProps = (state, ownProps) => { // eslint-disable-line
  const currentUser = JSON.parse(window.sessionStorage.getItem('currentUser'));
  return {
    currentUser,
    data: '',
    rows: [0, 1, 2, 3, 4, 5, 6],
    cols: [0, 1, 2, 3, 4, 5, 6],
    shipSize: 3,
  };
};
const mapDispatchToProps = (dispatch) => bindActionCreators(actions, dispatch);


export default connect(mapStateToProps, mapDispatchToProps)(PlaceMyShip);
