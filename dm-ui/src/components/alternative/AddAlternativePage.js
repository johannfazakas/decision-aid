import React, { useEffect, useState } from "react";

import AlternativeForm from "./AlternativeForm";

import { addAlternative } from "../../api/alternativesApi";
import { getDecision } from "../../api/decisionsApi";
import { setProperty } from "../../api/propertyApi";

const AddAlternativePage = props => {
  const [alternative, setAlternative] = useState({
    name: "",
  });
  const [criteria, setCriteria] = useState([]);
  const [properties, setProperties] = useState([]);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    getDecision(props.match.params.decisionId)
      .then(decision => {
        setCriteria(decision.criteria)
      })
  }, [props.match.params.decisionId])

  const handleChange = ({target}) => {
    setAlternative({
      ...alternative,
      [target.name]: target.value
    });
  };

  const handlePropertyChange = property => property.value ? upsertProperty(property) : removeProperty(property)

  const upsertProperty = property => {
    setProperties(
      [...(properties.filter(p => p.criteriaId !== property.criteriaId)),
        property]);
  }

  const removeProperty = property => {
    setProperties(properties.filter(p => p.criteriaId !== property.criteriaId));
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!formIsValid()) return;
    addAlternative(props.match.params.decisionId, {name: alternative.name})
      .then(_alternative =>
        Promise.all(properties.map(property =>
          setProperty(props.match.params.decisionId, {...property, ...{alternativeId: _alternative.id}}))))
      .then(() => navigateToDecisionDetails());
  };

  const formIsValid = () => {
    const _errors = {};
    if (!alternative.name) _errors.name = "Name is required";
    setErrors(_errors);
    return Object.keys(_errors).length === 0;
  };

  const handleCancel = () => {
    navigateToDecisionDetails();
  };

  const navigateToDecisionDetails = () => {
    props.history.push("/decision/" + props.match.params.decisionId + "/details");
  };

  return (
    <div className="jumbotron">
      <h1>Add alternative</h1>
      <AlternativeForm
        alternative={alternative}
        criteria={criteria}
        properties={properties}
        errors={errors}
        onChange={handleChange}
        onPropertyChange={handlePropertyChange}
        onSubmit={handleSubmit}
        onCancel={handleCancel}
      />
    </div>
  );
};

export default AddAlternativePage;