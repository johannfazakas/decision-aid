import React, { useState } from "react";

import AlternativeForm from "./AlternativeForm";

import { addAlternative } from "../api/alternativesApi";

const AddAlternativePage = props => {
  const [errors, setErrors] = useState({});
  const [alternative, setAlternative] = useState({
    name: ""
  });
  const decisionId = props.match.params.decisionId;

  const handleChange = ({target}) => {
    setAlternative({
      ...alternative,
      [target.name]: target.value
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!formIsValid()) return;
    addAlternative(decisionId, alternative)
      .then(() => navigateToDecisionDetails());
  };

  const formIsValid = () => {
    const _errors = {};
    if (!alternative.name) _errors.name = "Name is required";
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
      <h1>Add alternative</h1>
      <AlternativeForm
        alternative={alternative}
        errors={errors}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancel={handleCancel}
      />
    </div>
  );
};

export default AddAlternativePage;