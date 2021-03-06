import React from "react";
import PropTypes from "prop-types";

const TextInput = props => {
  let wrapperClass = "form-group";
  if (props.error.length > 0) {
    wrapperClass += " has-error";
  }

  return (
    <div className={wrapperClass}>
      <label htmlFor={props.id}>{props.label}</label>
      <div className="field">
        <input
          id={props.id}
          type={props.type}
          onChange={props.onChange}
          name={props.name}
          className="form-control"
          value={props.value || ""}
        />
      </div>
      {props.error && <div className="alert text-danger">{props.error}</div>}
    </div>
  );
};

TextInput.propTypes = {
  id: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  error: PropTypes.string,
  type: PropTypes.string,
  onChange: PropTypes.func.isRequired,
};

TextInput.defaultProps = {
  error: "",
  type: "text"
};

export default TextInput;
