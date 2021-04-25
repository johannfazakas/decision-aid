import React from "react";
import PropTypes from "prop-types";
import TextInput from "../../common/TextInput";
import NumberInput from "../../common/NumberInput";
import { defaultProperty } from "../../../store/default";

const AlternativeForm = props => {

  return <form onSubmit={props.onSubmit}>
    <TextInput
      id="name"
      name="name"
      label="Name"
      value={props.alternative.name}
      onChange={props.onChange}
      error={props.errors.name}
    />

    <h4>Properties:</h4>
    {props.criteria.map(criteria => (
      <NumberInput
        key={criteria.id}
        number={criteria.property || defaultProperty}
        label={criteria.name + " - " + criteria.weight + "%"}
        onChange={event => props.onPropertyChange(criteria.id, event.target.value)}
        id={criteria.name}
        name={criteria.name}
      />
    ))}
    <input type="submit" value="Save" className="btn btn-warning" />
    <div className="btn btn-light" onClick={props.onCancel}>Cancel</div>
  </form>;
};

AlternativeForm.propTypes = {
  alternative: PropTypes.object.isRequired,
  criteria: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired,
      weight: PropTypes.number.isRequired,
      property: PropTypes.object
    }).isRequired
  ).isRequired,
  errors: PropTypes.object.isRequired,
  onChange: PropTypes.func.isRequired,
  onPropertyChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onCancel: PropTypes.func.isRequired
};

export default AlternativeForm;
