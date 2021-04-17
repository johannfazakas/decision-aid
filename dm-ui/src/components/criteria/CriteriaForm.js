import React from "react";
import PropTypes from "prop-types";
import TextInput from "../common/TextInput";

const CriteriaForm = props => {

  return (
    <form onSubmit={props.onSubmit}>
      <TextInput
        id="name"
        name="name"
        label="Name"
        value={props.criteria.name}
        error={props.errors.name}
        onChange={props.onChange}
      />
      <input type="submit" value="Save" className="btn btn-warning" />
      <div className="btn btn-light" onClick={props.onCancel}>Cancel</div>
    </form>
  );
};

CriteriaForm.propTypes = {
  criteria: PropTypes.shape({
    name: PropTypes.string.isRequired
  }).isRequired,
  errors: PropTypes.shape({
    name: PropTypes.string
  }).isRequired,
  onChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onCancel: PropTypes.func.isRequired
}

export default CriteriaForm;
