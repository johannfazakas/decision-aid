import React from "react";
import { useState } from "react";

import CriteriaForm from "./CriteriaForm";
import { addCriteria } from "../../api/criteriaApi";

const AddCriteriaPage = props => {
  const [criteria, setCriteria] = useState({
    name: ""
  });
  const [errors, setErrors] = useState({});

  const handleChange = ({target}) => {
    setCriteria({
      ...criteria,
      [target.name]: target.value
    })
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!formIsValid()) return;
    addCriteria(props.match.params.decisionId, criteria)
      .then(() => navigateToDecisionDetails());
  };

  const formIsValid = () => {
    const _errors = {};
    if (!criteria.name) _errors.name = "Name is required";
    setErrors(_errors);
    return Object.keys(_errors).length === 0;
  };

  const handleCancel = () => {
    navigateToDecisionDetails();
  };

  const navigateToDecisionDetails = () => {
    props.history.push("/decision/" + props.match.params.decisionId + "/details");
  };

  return (
    <div className="jumbotron">
      <h1>Add Criteria</h1>
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

export default AddCriteriaPage;
