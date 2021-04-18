import React from "react";
import PropTypes from "prop-types";

const PropertyInput = props => {
  return (
    <div className="form-group">
      <label htmlFor={props.criteria.name}>{props.criteria.name}</label>
      <div className="field">
        <input
          id={props.criteria.name}
          type="number"
          name={props.criteria.name}
          onChange={props.onChange}
          className="form-control"
          defaultValue={props.property && props.property.value}
        />
      </div>
    </div>
  );
};

PropertyInput.propTypes = {
  property: PropTypes.shape({
    value: PropTypes.number,
  }),
  criteria: PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired
  }).isRequired,
  onChange: PropTypes.func.isRequired
};

export default PropertyInput;
