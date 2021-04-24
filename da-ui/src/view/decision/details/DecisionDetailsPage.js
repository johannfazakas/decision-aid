import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import DecisionTable from "./DecisionTable";

import { deleteDecision, getDecision } from "../../../api/decisionApi";
import { deleteAlternative } from "../../../api/alternativesApi";
import { deleteCriteria } from "../../../api/criteriaApi";

const DecisionDetailsPage = props => {
  const [decision, setDecision] = useState({
    id: "",
    name: "",
    description: "",
    criteria: [],
    alternatives: [],
    properties: []
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

  const handleDeleteAlternative = alternativeId => {
    deleteAlternative(decision.id, alternativeId)
      .then(() => {
        setDecision({
          ...decision,
          alternatives: decision.alternatives.filter(a => a.id !== alternativeId)
        })
      })
  }

  const handleDeleteCriteria = criteriaId => {
    deleteCriteria(decision.id, criteriaId)
      .then(() => {
        setDecision({
          ...decision,
          criteria: decision.criteria.filter(c => c.id !== criteriaId)
        })
      })
  }

  const handlePropertyUpdated = (alternativeId, criteriaId, value) => {
    console.log("property updated. alternativeId = " + alternativeId + ", criteriaId = " + criteriaId + ", value = " + value);
  }

  return (
    <div className="jumbotron">
      <h1>{decision.name}</h1>
      <h5>{decision.description}</h5>
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
        onDeleteAlternative={handleDeleteAlternative}
        onDeleteCriteria={handleDeleteCriteria}
        onPropertyUpdated={handlePropertyUpdated}
      />
    </div>
  );
};

export default DecisionDetailsPage;
