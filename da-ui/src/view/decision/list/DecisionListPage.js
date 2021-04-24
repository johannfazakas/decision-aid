import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import * as PropTypes from "prop-types";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";

import * as decisionActions from "../../../action/decisionActions";

import DecisionList from "./DecisionList";

const DecisionListPage = ({decisions, loadDecisions, deleteDecision}) => {

  useEffect(() => {
    if (decisions.length === 0) {
      loadDecisions()
        .catch(error => {
          alert("Loading courses failed." + error);
        });
    }
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
  deleteDecision: PropTypes.func.isRequired
}

const mapStateToProps = state => ({
  decisions: Object.values(state.decisions),
});

const mapDispatchToProps = dispatch => ({
  loadDecisions: bindActionCreators(decisionActions.loadDecisions, dispatch),
  deleteDecision: bindActionCreators(decisionActions.deleteDecision, dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(DecisionListPage);
