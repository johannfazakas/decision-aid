import React, { useEffect, useState } from "react";

import CriteriaForm from "./CriteriaForm";

import { getDecision } from "../../api/decisionsApi";
import { updateCriteria } from "../../api/criteriaApi";

const UpdateCriteriaPage = props => {
  const [criteria, setCriteria] = useState({
    id: "",
    name: ""
  });
  const [errors, setErrors] = useState({});

  useEffect(() => {
    const decisionId = props.match.params.decisionId;
    const criteriaId = props.match.params.criteriaId;
    getDecision(decisionId).then(decision => {
      setCriteria(decision.criteria.filter(c => c.id === criteriaId)[0])
    });
  }, [props.match.params.decisionId, props.match.params.criteriaId]);

  const handleChange = ({target}) => {
    setCriteria({
      ...criteria,
      [target.name]: target.value
    })
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!formIsValid()) return;
    updateCriteria(props.match.params.decisionId, criteria)
      .then(navigateToDecisionDetails);
  };

  const handleCancel = () => navigateToDecisionDetails();

  const formIsValid = () => {
    const _errors = {};
    if (!criteria.name) _errors.name = "Name is required";
    setErrors(_errors);
    return Object.keys(_errors).length === 0;
  };

  const navigateToDecisionDetails = () => {
    props.history.push("/decision/" + props.match.params.decisionId + "/details")
  }

  return (
    <div className="jumbotron">
      <h1>Update Criteria</h1>
      <CriteriaForm
        criteria={criteria}
        errors={errors}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancel={handleCancel}
      />
    </div>
  );
};

export default UpdateCriteriaPage;
