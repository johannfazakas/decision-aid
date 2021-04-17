import React, { useEffect, useState } from "react";
import { getDecision, updateDecision } from "../../api/decisionsApi";

import DecisionForm from "./DecisionForm";

const UpdateDecisionPage = props => {
  const [errors, setErrors] = useState({});
  const [decision, setDecision] = useState({
    id: null,
    name: "",
    description: ""
  });

  useEffect(() => {
    const decisionId = props.match.params.decisionId;
    getDecision(decisionId).then(_decision => setDecision(_decision));
  }, [props.match.params.decisionId]);

  const handleChange = ({target}) => {
    setDecision({
      ...decision,
      [target.name]: target.value
    })
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!formIsValid()) return;
    updateDecision(decision)
      .then(() => props.history.push("/decision/" + decision.id + "/details"));
  };

  const handleCancel = () => {
    props.history.push("/decision/" + decision.id + "/details");
  };

  const formIsValid = () => {
    const _errors = {};
    if (!decision.name) _errors.name = "Name is required";
    setErrors(_errors);
    return Object.keys(_errors).length === 0;
  };

  return (
    <div className="jumbotron">
      <h2>Update decision</h2>
      <DecisionForm
        decision={decision}
        errors={errors}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancel={handleCancel}
      />
    </div>
  );
}

export default UpdateDecisionPage;
