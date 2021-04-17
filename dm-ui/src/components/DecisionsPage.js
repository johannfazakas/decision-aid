import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import DecisionList from "./DecisionList";

import { deleteDecision, listDecisions } from "../api/decisionsApi";

const DecisionsPage = () => {
  const [decisions, setDecisions] = useState([]);

  useEffect(() => {
    listDecisions()
      .then(response => setDecisions(response.items));
  }, []);

  const handleDelete = decisionId => {
    deleteDecision(decisionId)
      .then(_ => setDecisions(decisions.filter(d => d.id !== decisionId)));
  };

  return (
    <div className="jumbotron">
      <h1>My decisions</h1>
      <DecisionList decisions={decisions} onDelete={handleDelete}/>
      <Link to="/decision" className="btn btn-primary">
        Create new
      </Link>
    </div>
  );
}

export default DecisionsPage;
