import React, { useEffect, useState } from "react";

import AlternativeForm from "./AlternativeForm";

import { getDecision } from "../../api/decisionsApi";
import { updateAlternative } from "../../api/alternativesApi"

const UpdateAlternativePage = props => {
  const [alternative, setAlternative] = useState({
    id: null,
    name: ""
  });
  const [errors, setErrors] = useState({});

  useEffect(() => {
    const decisionId = props.match.params.decisionId;
    const alternativeId = props.match.params.alternativeId;
    getDecision(decisionId).then(decision => {
      setAlternative(decision.alternatives.filter(a => a.id === alternativeId)[0])
    });
  }, [props.match.params.decisionId, props.match.params.alternativeId]);

  const handleChange = ({target}) => {
    setAlternative({
      ...alternative,
      [target.name]: target.value
    })
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!formIsValid()) return;
    updateAlternative(props.match.params.decisionId, alternative)
      .then(navigateToDecisionDetails);
  };

  const handleCancel = () => navigateToDecisionDetails();

  const formIsValid = () => {
    const _errors = {};
    if (!alternative.name) _errors.name = "Name is required";
    setErrors(_errors);
    return Object.keys(_errors).length === 0;
  };

  const navigateToDecisionDetails = () => {
    props.history.push("/decision/" + props.match.params.decisionId + "/details")
  }

  return (
    <div className="jumbotron">
      <h1>Update alternative</h1>
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

export default UpdateAlternativePage;
