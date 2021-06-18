import React, { useEffect, useState } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import PropTypes from "prop-types";

import { loadDecisions } from "../../../action/decisionActions";
import { addAlternative, updateAlternative } from "../../../action/alternativeActions";
import { getAlternativeById, getCriteria, getProperty } from "../../../store/decisionSelector";
import { defaultAlternative } from "../../../store/default";

import AlternativeForm from "./AlternativeForm";

const AlternativePage = props => {

  const [alternative, setAlternative] = useState({...props.alternative})
  const [properties, setProperties] = useState([])
  const [errors, setErrors] = useState({})

  useEffect(() => {
    if (!props.user.token) {
      props.history.push("/")
    }
    if (props.decisions.length === 0) {
      props.loadDecisions(props.user.token)
    } else {
      setAlternative({...props.alternative})
    }
  }, [props.alternative, props.user])

  const navigateBack = () => props.history.push("/decision/" + props.decisionId + "/details")

  const validateForm = () => {
    const _errors = {}
    if (!alternative.name) _errors.name = "Name is required"
    setErrors(_errors)
    return Object.keys(_errors).length === 0
  }

  const handleSubmit = event => {
    event.preventDefault()
    if (!validateForm()) return
    const savedAlternative = alternative.id
      ? props.updateAlternative(props.decisionId, alternative, properties, props.user.token)
      : props.addAlternative(props.decisionId, alternative, properties, props.user.token)
    savedAlternative.then(navigateBack)
  }

  const handleChange = event => {
    const {name, value} = event.target
    setAlternative({
      ...alternative,
      [name]: value
    })
  }

  const handlePropertyChange = (criteriaId, value) =>
    setProperties([
      ...properties.filter(property => property.criteriaId !== criteriaId),
      {criteriaId, value: parseInt(value)}
    ])

  return (
    <div className="jumbotron">
      <h1>{alternative.id ? "Update" : "Add"} alternative</h1>
      <AlternativeForm
        alternative={alternative}
        criteria={props.criteria}
        errors={errors}
        onChange={handleChange}
        onPropertyChange={handlePropertyChange}
        onSubmit={handleSubmit}
        onCancel={navigateBack}
      />
    </div>
  )
}

AlternativePage.propTypes = {
  user: PropTypes.object.isRequired,
  decisions: PropTypes.array.isRequired,
  decisionId: PropTypes.string.isRequired,
  alternative: PropTypes.object.isRequired,
  criteria: PropTypes.array.isRequired,
  loadDecisions: PropTypes.func.isRequired,
  addAlternative: PropTypes.func.isRequired,
  updateAlternative: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired
}

const mapStateToProps = (state, props) => {
  const {decisionId, alternativeId} = props.match.params
  return {
    user: state.user,
    decisions: Object.values(state.decisions),
    decisionId,
    alternative: alternativeId
      ? getAlternativeById(state, decisionId, alternativeId) || defaultAlternative
      : defaultAlternative,
    criteria: getCriteria(state, decisionId)
      .map(criteria => ({...criteria, property: getProperty(state, decisionId, criteria.id, alternativeId)}))
  }
}

const mapDispatchToProps = dispatch => ({
  loadDecisions: bindActionCreators(loadDecisions, dispatch),
  addAlternative: bindActionCreators(addAlternative, dispatch),
  updateAlternative: bindActionCreators(updateAlternative, dispatch),
})

export default connect(mapStateToProps, mapDispatchToProps)(AlternativePage)
