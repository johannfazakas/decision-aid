import React, { useEffect, useState } from "react";

import AlternativeForm from "./AlternativeForm";

import { getDecision } from "../../../api/decisionApi";
import { updateAlternative } from "../../../api/alternativesApi"
import { setProperty } from "../../../api/propertyApi";

const UpdateAlternativePage = props => {
  const [alternative, setAlternative] = useState({
    id: null,
    name: "",
    properties: []
  });
  const [criteria, setCriteria] = useState([]);
  const [properties, setProperties] = useState([]);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    const decisionId = props.match.params.decisionId;
    const alternativeId = props.match.params.alternativeId;
    getDecision(decisionId).then(decision => {
      const _alternative = decision.alternatives.filter(a => a.id === alternativeId)[0]
      setAlternative(_alternative)
      setCriteria(decision.criteria);
      setProperties(decision.properties.filter(p => p.alternativeId === _alternative.id));
    });
  }, [props.match.params.decisionId, props.match.params.alternativeId]);

  const handleChange = ({target}) => {
    setAlternative({
      ...alternative,
      [target.name]: target.value
    })
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
    updateAlternative(props.match.params.decisionId, alternative)
      .then(() =>
        Promise.all(properties.map(property =>
          setProperty(props.match.params.decisionId, {...property, ...{alternativeId: alternative.id}}))))
      .then(navigateToDecisionDetails);
  };

  const handleCancel = () => navigateToDecisionDetails();

  const formIsValid = () => {
    const _errors = {};
    if (!alternative.name) _errors.name = "Name is required";
    setErrors(_errors);
    return Object.keys(_errors).length === 0;
  };

  const navigateToDecisionDetails = () => {
    props.history.push("/decision/" + props.match.params.decisionId + "/details")
  }

  return (
    <div className="jumbotron">
      <h1>Update alternative</h1>
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

export default UpdateAlternativePage;
