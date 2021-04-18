import React from "react";
import PropTypes from "prop-types";

const OptionalNumberInput = props => {
  return (
    <div className="form-group">
      <label htmlFor={props.name}>{props.label}</label>
      <div className="field">
        <input
          id={props.id}
          type="number"
          name={props.name}
          onChange={props.onChange}
          className="form-control"
          defaultValue={props.number && props.number.value}
        />
      </div>
    </div>
  );
};

OptionalNumberInput.propTypes = {
  number: PropTypes.shape({
    value: PropTypes.number,
  }),
  id: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired
};

export default OptionalNumberInput;
