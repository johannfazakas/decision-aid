import React from "react";
import PropTypes from "prop-types";

const NumberInput = props => {
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
      {props.error && <div className="alert alert-danger">{props.error}</div>}
    </div>
  );
};

NumberInput.propTypes = {
  number: PropTypes.shape({
    value: PropTypes.number,
  }),
  id: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  error: PropTypes.string,
  onChange: PropTypes.func.isRequired
};

NumberInput.defaultProps = {
  error: ""
};

export default NumberInput;
