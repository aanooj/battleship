import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as actions from '../actions';

import SignUpComponent from '../components/GameSignUp';

const mapStateToProps = (state={}) => { // eslint-disable-line
  return {
    state,
  };
};
const mapDispatchToProps = (dispatch) => bindActionCreators(actions, dispatch);


export default connect(mapStateToProps, mapDispatchToProps)(SignUpComponent);
