/**
 * Created by sgumma on 27-06-2017.
 */
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as actions from '../actions';

import GameBoard from '../components/GameBoard';

const mapStateToProps = (state, ownProps) => { // eslint-disable-line
  const currentUser = JSON.parse(window.sessionStorage.getItem('currentUser'));
  const data = state.toJS();
  return {
    data,
    currentUser,
  };
};
const mapDispatchToProps = (dispatch) => bindActionCreators(actions, dispatch);


export default connect(mapStateToProps, mapDispatchToProps)(GameBoard);
