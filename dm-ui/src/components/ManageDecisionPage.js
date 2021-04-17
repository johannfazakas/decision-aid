import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { deleteDecision, getDecision } from "../api/decisionsApi";

const ManageDecisionPage = props => {
  const [decision, setDecision] = useState({
    id: null,
    name: "",
    description: ""
  });

  useEffect(() => {
    const decisionId = props.match.params.decisionId;
    getDecision(decisionId).then(_decision => setDecision(_decision));
  }, [props.match.params.decisionId]);

  const handleDelete = () => {
    deleteDecision(props.match.params.decisionId)
      .then(() => props.history.push("/decisions"));
  };

  return (
    <div className="jumbotron">
      <h1>{decision.name}</h1>
      <h3>{decision.description}</h3>
      <br />
      <Link
        to={"/decision/" + props.match.params.decisionId}
        className="btn btn-secondary m-1"
      >
        Update
      </Link>
      <Link
        to={"/decision/" + props.match.params.decisionId + "/criteria"}
        className="btn btn-secondary m-1"
      >
        New criteria
      </Link>
      <div
        className="btn btn-danger m-1"
        onClick={handleDelete}
      >
        Delete
      </div>
    </div>
  );
};

export default ManageDecisionPage;
