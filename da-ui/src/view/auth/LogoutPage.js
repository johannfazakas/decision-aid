import React, { useEffect } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as PropTypes from "prop-types";
import { logoutUser } from "../../action/userActions";

const LogoutPage = props => {

  useEffect(() => {
    if (props.user.token) {
      props.logoutUser(props.user)
        .then(() => props.history.push("/login"))
    }
    props.history.push("/login")
  }, [props.user])

  return (
    <div className="jumbotron">
      <h1>Bye</h1>
      <p>See you soon</p>
    </div>
  );
}

LogoutPage.propTypes = {
  user: PropTypes.object.isRequired,
  logoutUser: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  user: state.user
})

const mapDispatchToProps = dispatch => ({
  logoutUser: bindActionCreators(logoutUser, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(LogoutPage);
