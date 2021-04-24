import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import * as PropTypes from "prop-types";

import * as decisionActions from "../../../action/decisionActions";

import DecisionList from "./DecisionList";
import { bindActionCreators } from "redux";

const DecisionListPage = ({decisions, loadDecisions, deleteDecision}) => {

  useEffect(() => {
    if (decisions.length === 0) loadDecisions();
  }, []);

  return (
    <div className="jumbotron">
      <h1>My decisions</h1>
      <Link to="/decision" className="btn btn-dark">New</Link>
      <DecisionList decisions={decisions} deleteDecision={deleteDecision} />
    </div>
  );
};

DecisionListPage.propTypes = {
  decisions: PropTypes.array.isRequired,
  loadDecisions: PropTypes.func.isRequired,
}

const mapStateToProps = state => ({
  decisions: state.decisions,
});

const mapDispatchToProps = dispatch => ({
  loadDecisions: bindActionCreators(decisionActions.loadDecisions, dispatch),
  deleteDecision: bindActionCreators(decisionActions.deleteDecision, dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(DecisionListPage);
