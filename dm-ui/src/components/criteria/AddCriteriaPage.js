import React, { useEffect, useState } from "react";

import CriteriaForm from "./CriteriaForm";

import { addCriteria } from "../../api/criteriaApi";
import { getDecision } from "../../api/decisionsApi";
import { setProperty } from "../../api/propertyApi";

const AddCriteriaPage = props => {
  const [criteria, setCriteria] = useState({
    name: "",
  });
  const [alternatives, setAlternatives] = useState([]);
  const [properties, setProperties] = useState([]);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    getDecision(props.match.params.decisionId)
      .then(decision => {
        setAlternatives(decision.alternatives);
      });
  }, [props.match.params.decisionId])

  const handleNameChange = ({target}) => {
    setCriteria({
      ...criteria,
      name: target.value
    })
  };

  const handleWeightChange = ({target}) => {
    setCriteria({
      ...criteria,
      weight: parseFloat(target.value)
    })
  };

  const handlePropertyChange = property => property.value ?
    upsertProperty(property) :
    removeProperty(property)

  const upsertProperty = property => {
    setProperties(
      [...(properties.filter(p => p.alternativeId !== property.alternativeId)),
        property]);
  }

  const removeProperty = property => {
    setProperties(properties.filter(p => p.alternativeId !== property.alternativeId));
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!formIsValid()) return;
    addCriteria(props.match.params.decisionId, criteria)
      .then(criteria =>
        Promise.all(properties.map(property =>
          setProperty(props.match.params.decisionId, {...property, ...{criteriaId: criteria.id}}))))
      .then(() => navigateToDecisionDetails());
  };

  const formIsValid = () => {
    const _errors = {};
    if (!criteria.name) _errors.name = "Name is required";
    if (!criteria.weight || criteria.weight < 1 || criteria.weight > 100) {
      _errors.weight = "Weight value should be between 1 and 100"
    }
    debugger;
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
      <h1>Add Criteria</h1>
      <CriteriaForm
        criteria={criteria}
        alternatives={alternatives}
        properties={properties}
        errors={errors}
        onNameChange={handleNameChange}
        onWeightChange={handleWeightChange}
        onPropertyChange={handlePropertyChange}
        onSubmit={handleSubmit}
        onCancel={handleCancel}
      />
    </div>
  );
};

export default AddCriteriaPage;
