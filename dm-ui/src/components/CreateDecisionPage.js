import React, { useState } from "react";
import { createDecision } from "../api/decisionsApi";

import DecisionForm from "./DecisionForm";

const CreateDecisionPage = ({history}) => {
  const [decision, setDecision] = useState({name: ""});

  function handleChange({target}) {
    setDecision({
      ...decision,
      [target.name]: target.value
    })
  }

  function handleSubmit(event) {
    event.preventDefault();
    createDecision(decision)
      .then(() => history.push("/decisions"));
  }

  return (
    <>
      <h2>Add a new decision</h2>
      <DecisionForm
        decision={decision}
        onChange={handleChange}
        onSubmit={handleSubmit} />
    </>
  );
}

export default CreateDecisionPage;
