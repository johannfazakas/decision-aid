import React from "react";
import PropTypes from "prop-types";
import TextInput from "../common/TextInput";
import NumberInput from "../common/NumberInput";

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
    <hr />
    <h4>Properties:</h4>
    {props.criteria
      .map(criteria => {
        const property = props.properties
          .filter(p => p.criteriaId === criteria.id)
          [0] || {};
        return [criteria, property];
      })
      .map(([criteria, property]) => {
        return <NumberInput
          key={criteria.id}
          id={criteria.name}
          name={criteria.name}
          label={criteria.name + " - " + criteria.weight + "%"}
          number={property}
          onChange={({target}) => props.onPropertyChange({
            criteriaId: criteria.id,
            value: parseFloat(target.value)
          })}
        />
      })}

    <input type="submit" value="Save" className="btn btn-warning" />
    <div className="btn btn-light" onClick={props.onCancel}>Cancel</div>
  </form>;
};

AlternativeForm.propTypes = {
  alternative: PropTypes.shape({
    id: PropTypes.string,
    name: PropTypes.string.isRequired,
  }).isRequired,
  criteria: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired,
      weight: PropTypes.number.isRequired
    }).isRequired
  ).isRequired,
  properties: PropTypes.arrayOf(
    PropTypes.shape({
      criteriaId: PropTypes.string.isRequired,
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
};

export default AlternativeForm;
