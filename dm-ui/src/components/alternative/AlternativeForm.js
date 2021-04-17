import React from "react";
import PropTypes from "prop-types";
import TextInput from "../common/TextInput";

const AlternativeForm = props => {
  return (
    <form onSubmit={props.onSubmit}>
      <TextInput
        id="name"
        name="name"
        label="Name"
        value={props.alternative.name}
        onChange={props.onChange}
        error={props.errors.name}
      />
      <input type="submit" value="Save" className="btn btn-warning" />
      <div className="btn btn-light" onClick={props.onCancel}>Cancel</div>
    </form>
  );
};

AlternativeForm.propTypes = {
  alternative: PropTypes.shape({
    name: PropTypes.string.isRequired
  }).isRequired,
  errors: PropTypes.shape({
    name: PropTypes.string
  }).isRequired,
  onChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onCancel: PropTypes.func.isRequired
};

export default AlternativeForm;
