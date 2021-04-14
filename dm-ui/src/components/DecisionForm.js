import React from "react";
import PropTypes from "prop-types";
import TextInput from "./common/TextInput";

const DecisionForm = props => {
  return (
    <form onSubmit={props.onSubmit}>
      <TextInput
        id="name"
        name="name"
        label="Name"
        value={props.decision.name}
        onChange={props.onChange}
      />
      <TextInput
        id="description"
        name="description"
        label="Description"
        value={props.decision.description}
        onChange={props.onChange}
      />
      <input type="submit" value="Save" className="btn btn-primary" />
    </form>
  )
};

DecisionForm.propTypes = {
  decision: PropTypes.shape({
    name: PropTypes.string,
    description: PropTypes.string
  }).isRequired,
  onChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired
}

export default DecisionForm;
