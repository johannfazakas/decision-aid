import React from "react";
import PropTypes from "prop-types";
import TextInput from "../../common/TextInput";
import NumberInput from "../../common/NumberInput";

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
      {/*<hr />*/}
      {/*<h4>Properties:</h4>*/}
      {/*{props.alternatives*/}
      {/*  .map(alternative => {*/}
      {/*    const property = props.properties*/}
      {/*      .filter(p => p.alternativeId === alternative.id)[0] || {};*/}
      {/*    return [alternative, property]*/}
      {/*  })*/}
      {/*  .map(([alternative, property]) => {*/}
      {/*    return <NumberInput*/}
      {/*      key={alternative.id}*/}
      {/*      id={alternative.name}*/}
      {/*      name={alternative.name}*/}
      {/*      label={alternative.name}*/}
      {/*      number={props.properties.filter(p => p.alternativeId === alternative.id)[0]}*/}
      {/*      onChange={({target}) => props.onPropertyChange({*/}
      {/*        alternativeId: alternative.id,*/}
      {/*        value: parseFloat(target.value)*/}
      {/*      })}*/}
      {/*    />*/}
      {/*  })*/}
      {/*}*/}
      <input type="submit" value="Save" className="btn btn-warning" />
      <div className="btn btn-light" onClick={props.onCancel}>Cancel</div>
    </form>
  );
};

CriteriaForm.propTypes = {
  criteria: PropTypes.object.isRequired,
  // alternatives: PropTypes.array.isRequired,
  // properties: PropTypes.array.isRequired,
  errors: PropTypes.object.isRequired,
  onChange: PropTypes.func.isRequired,
  // onPropertyChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onCancel: PropTypes.func.isRequired
}

export default CriteriaForm;
