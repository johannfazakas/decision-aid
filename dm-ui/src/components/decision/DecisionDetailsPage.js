import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { deleteDecision, getDecision } from "../../api/decisionsApi";
import DecisionTable from "./DecisionTable";

const DecisionDetailsPage = props => {
  const [decision, setDecision] = useState({
    id: null,
    name: "",
    description: "",
    criteria: [],
    alternatives: []
  });

  useEffect(() => {
    const decisionId = props.match.params.decisionId;
    getDecision(decisionId).then(_decision => setDecision(_decision));
  }, [props.match.params.decisionId]);

  const handleDelete = () => {
    deleteDecision(props.match.params.decisionId)
      .then(() => props.history.push("/decisions"));
  };

  const handleAddCriteria = () => {
    props.history.push("/decision/" + props.match.params.decisionId + "/criteria");
  }

  const handleAddAlternative = () => {
    props.history.push("/decision/" + props.match.params.decisionId + "/alternative");
  }

  return (
    <div className="jumbotron">
      <h1>{decision.name}</h1>
      <h3>{decision.description}</h3>
      <br />

      <Link
        to={"/decision/" + props.match.params.decisionId}
        className="btn btn-dark m-1"
      >
        Update
      </Link>
      <div
        className="btn btn-danger m-1"
        onClick={handleDelete}
      >
        Delete
      </div>
      <DecisionTable
        decision={decision}
        onAddCriteria={handleAddCriteria}
        onAddAlternative={handleAddAlternative}
      />
    </div>
  );
};

export default DecisionDetailsPage;
