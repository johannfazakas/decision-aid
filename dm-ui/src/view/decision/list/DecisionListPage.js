import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import DecisionList from "./DecisionList";

import * as decisionActions from "../../../action/decisionActions"

import decisionStore from "../../../store/decisionStore";

const DecisionListPage = () => {
  const [decisions, setDecisions] = useState(decisionStore.getDecisions());

  useEffect(() => {
    decisionStore.addChangeListener(onChange);
    if (decisionStore.getDecisions().length === 0) decisionActions.loadDecisions();
    return () => decisionStore.removeChangeListener(onChange);
  }, []);

  const onChange = () => setDecisions(decisionStore.getDecisions());

  const handleDelete = decisionId => decisionActions.deleteDecision(decisionId);

  return (
    <div className="jumbotron">
      <h1>My decisions</h1>
      <DecisionList decisions={decisions} deleteCourse={handleDelete} />
      <Link to="/decision" className="btn btn-dark">
        New
      </Link>
    </div>
  );
};

export default DecisionListPage;
