import React from "react";
import PropTypes from "prop-types";
import TextInput from "../../common/TextInput";
import NumberInput from "../../common/NumberInput";
import { defaultProperty } from "../../../store/default";

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
      <NumberInput
        id="weight"
        name="weight"
        label="Weight"
        number={{value: props.criteria.weight}}
        error={props.errors.weight}
        onChange={props.onChange}
      />

      <h4>Properties:</h4>
      {props.alternatives.map(alternative => (
        <NumberInput
          key={alternative.id}
          number={alternative.property || defaultProperty}
          label={alternative.name}
          onChange={event => props.onPropertyChange(alternative.id, event.target.value)}
          id={alternative.name}
          name={alternative.name}
        />
      ))}
      <input type="submit" value="Save" className="btn btn-warning" />
      <div className="btn btn-light" onClick={props.onCancel}>Cancel</div>
    </form>
  );
};

CriteriaForm.propTypes = {
  criteria: PropTypes.object.isRequired,
  alternatives: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired,
      property: PropTypes.object
    }).isRequired
  ).isRequired,
  errors: PropTypes.object.isRequired,
  onChange: PropTypes.func.isRequired,
  onPropertyChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onCancel: PropTypes.func.isRequired
}

export default CriteriaForm;
