import React, { useEffect, useState } from "react"
import { bindActionCreators } from "redux"
import { connect } from "react-redux"
import * as PropTypes from "prop-types";

import { loadDecisions } from "../../../action/decisionActions"
import { addCriteria, updateCriteria } from "../../../action/criteriaActions";
import { getAlternatives, getCriteriaById, getProperty } from "../../../store/decisionSelector";
import { defaultCriteria } from "../../../store/default";

import CriteriaForm from "./CriteriaForm"

const CriteriaPage = props => {

  const [criteria, setCriteria] = useState({...props.criteria})
  // TODO evaluate storing only updated alternatives/properties
  const [alternatives, setAlternatives] = useState([...props.alternatives])
  const [errors, setErrors] = useState({})

  useEffect(() => {
    if (props.decisions.length === 0) {
      props.loadDecisions()
        .catch(error => alert("Loading decisions failed. " + error))
    } else {
      setCriteria({...props.criteria})
      setAlternatives([...props.alternatives])
    }
  }, [props.criteria, props.alternatives])

  const validateForm = () => {
    const _errors = {}
    if (!criteria.name) _errors.name = "Name is required"
    if (!criteria.weight || criteria.weight < 1 || criteria.weight > 100) {
      _errors.weight = "Weight value should be between 1 and 100"
    }
    setErrors(_errors);
    return Object.keys(_errors).length === 0
  }

  const navigateBack = () => props.history.push("/decision/" + props.decisionId + "/details")

  const handleChange = event => {
    const {name, value} = event.target
    setCriteria(previousCriteria => ({
      ...previousCriteria,
      [name]: name === "weight" ? parseInt(value) : value
    }))
  }

  const handlePropertyChange = (alternativeId, value) =>
    setAlternatives(alternatives
      .map(alternative => alternative.id === alternativeId
        ? {...alternative, property: {alternativeId, value}}
        : alternative)
    )

  const handleSubmit = event => {
    event.preventDefault()
    if (!validateForm()) return
    const properties = alternatives
      .flatMap(alternative => alternative.property ? [alternative.property] : [])
    const saveCriteria = criteria.id
      ? props.updateCriteria(props.decisionId, criteria, properties)
      : props.addCriteria(props.decisionId, criteria, properties)
    saveCriteria.then(navigateBack)
  }

  return (
    <div className="jumbotron">
      <h1>{criteria.id ? "Update" : "Add"} Criteria</h1>
      <CriteriaForm
        criteria={criteria}
        alternatives={props.alternatives}
        errors={errors}
        onChange={handleChange}
        onPropertyChange={handlePropertyChange}
        onSubmit={handleSubmit}
        onCancel={navigateBack}
      />
    </div>
  );
}

CriteriaPage.propTypes = {
  decisions: PropTypes.array.isRequired,
  decisionId: PropTypes.string.isRequired,
  criteria: PropTypes.object.isRequired,
  alternatives: PropTypes.array.isRequired,
  loadDecisions: PropTypes.func.isRequired,
  addCriteria: PropTypes.func.isRequired,
  updateCriteria: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired,
}

const mapStateToProps = (state, props) => {
  const {decisionId, criteriaId} = props.match.params
  return {
    decisions: Object.values(state.decisions),
    decisionId,
    criteria: criteriaId
      ? getCriteriaById(state, decisionId, criteriaId) || defaultCriteria
      : defaultCriteria,
    alternatives: getAlternatives(state, decisionId)
      .map(alternative => ({...alternative, property: getProperty(state, decisionId, criteriaId, alternative.id)}))
  };
}

const mapDispatchToProps = dispatch => ({
  loadDecisions: bindActionCreators(loadDecisions, dispatch),
  addCriteria: bindActionCreators(addCriteria, dispatch),
  updateCriteria: bindActionCreators(updateCriteria, dispatch),
})

export default connect(mapStateToProps, mapDispatchToProps)(CriteriaPage)
