import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import * as PropTypes from "prop-types";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";

import * as decisionActions from "../../../action/decisionActions";

import DecisionList from "./DecisionList";

const DecisionListPage = props => {

  useEffect(() => {
    if (!props.user.token) {
      props.history.push("/")
    }
    if (props.decisions.length === 0) {
      props.loadDecisions()
        .catch(error => {
          alert("Loading courses failed." + error);
        });
    }
  }, [props.user]);

  return (
    <div className="jumbotron">
      <h1>My decisions</h1>
      <Link to="/decision" className="btn btn-dark">New</Link>
      <DecisionList decisions={props.decisions} deleteDecision={props.deleteDecision} />
    </div>
  );
};

DecisionListPage.propTypes = {
  decisions: PropTypes.array.isRequired,
  user: PropTypes.object.isRequired,
  history: PropTypes.object.isRequired,
  loadDecisions: PropTypes.func.isRequired,
  deleteDecision: PropTypes.func.isRequired
}

const mapStateToProps = state => ({
  decisions: Object.values(state.decisions),
  user: state.user
});

const mapDispatchToProps = dispatch => ({
  loadDecisions: bindActionCreators(decisionActions.loadDecisions, dispatch),
  deleteDecision: bindActionCreators(decisionActions.deleteDecision, dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(DecisionListPage);
