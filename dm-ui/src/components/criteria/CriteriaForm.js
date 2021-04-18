import React from "react";
import PropTypes from "prop-types";
import TextInput from "../common/TextInput";
import OptionalNumberInput from "../common/OptionalNumberInput";

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
      {props.alternatives
        .map(alternative => {
          const property = props.properties
            .filter(p => p.alternativeId === alternative.id)
            [0] || {};
          return [alternative, property]
        })
        .map(([alternative, property]) => {
          return <OptionalNumberInput
            key={alternative.id}
            id={alternative.name}
            name={alternative.name}
            label={alternative.name}
            number={props.properties.filter(p => p.alternativeId === alternative.id)[0]}
            onChange={({target}) => props.onPropertyChange({
              alternativeId: alternative.id,
              value: parseFloat(target.value)
            })}
          />
        })
      }
      <input type="submit" value="Save" className="btn btn-warning" />
      <div className="btn btn-light" onClick={props.onCancel}>Cancel</div>
    </form>
  );
};

CriteriaForm.propTypes = {
  criteria: PropTypes.shape({
    name: PropTypes.string.isRequired
  }).isRequired,
  alternatives: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired
    })
  ).isRequired,
  properties: PropTypes.arrayOf(
    PropTypes.shape({
      alternativeId: PropTypes.string.isRequired,
      value: PropTypes.number.isRequired
    })
  ).isRequired,
  errors: PropTypes.shape({
    name: PropTypes.string
  }).isRequired,
  onChange: PropTypes.func.isRequired,
  onPropertyChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onCancel: PropTypes.func.isRequired
}

export default CriteriaForm;
