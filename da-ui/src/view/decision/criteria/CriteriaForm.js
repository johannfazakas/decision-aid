import React from "react";
import PropTypes from "prop-types";
import TextInput from "../../common/TextInput";
import NumberInput from "../../common/NumberInput";
import { defaultProperty } from "../../../store/default";
import SelectInput from "../../common/SelectInput";

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
      <SelectInput
        name="type"
        label="Type"
        defaultOption="Select type"
        value={props.criteria.type || ""}
        options={[
          {text: "Maximum", value: "maximum"},
          {text: "Minimum", value: "minimum"},
        ]}
        error={props.errors.type}
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
      <TextInput
        id="unitOfMeasure"
        name="unitOfMeasure"
        label="Unit of measure"
        value={props.criteria.unitOfMeasure}
        error={props.errors.unitOfMeasure}
        onChange={props.onChange}
      />
      <h4>Properties:</h4>
      {props.alternatives.map(alternative => (
        <NumberInput
          id={alternative.name}
          name={alternative.name}
          label={alternative.name}
          number={alternative.property || defaultProperty}
          key={alternative.id}
          onChange={event => props.onPropertyChange(alternative.id, event.target.value)}
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
