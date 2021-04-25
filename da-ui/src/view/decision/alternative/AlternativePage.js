import React, { useEffect, useState } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import PropTypes from "prop-types";

import { loadDecisions } from "../../../action/decisionActions";
import { addAlternative, updateAlternative } from "../../../action/alternativeActions";
import { getAlternative } from "../../../store/decisionSelector";
import { defaultAlternative } from "../../../store/default";

import AlternativeForm from "./AlternativeForm";

const AlternativePage = props => {

  const [alternative, setAlternative] = useState({...props.alternative})
  const [errors, setErrors] = useState({})

  useEffect(() => {
    if (props.decisions.length === 0) {
      props.loadDecisions()
    } else {
      setAlternative({...props.alternative})
    }
  }, [props.alternative])

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
      ? props.updateAlternative(props.decisionId, alternative)
      : props.addAlternative(props.decisionId, alternative)
    savedAlternative.then(navigateBack)
  }

  const handleChange = event => {
    const {name, value} = event.target
    setAlternative({
      ...alternative,
      [name]: value
    })
  }

  return (
    <div className="jumbotron">
      <h1>{alternative.id ? "Update" : "Add"} alternative</h1>
      <AlternativeForm
        alternative={alternative}
        errors={errors}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancel={navigateBack}
      />
    </div>
  )
}

AlternativePage.propTypes = {
  decisions: PropTypes.array.isRequired,
  decisionId: PropTypes.string.isRequired,
  alternative: PropTypes.object.isRequired,
  loadDecisions: PropTypes.func.isRequired,
  addAlternative: PropTypes.func.isRequired,
  updateAlternative: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired
}

const mapStateToProps = (state, props) => {
  const {decisionId, alternativeId} = props.match.params
  return {
    decisions: Object.values(state.decisions),
    decisionId,
    alternative: alternativeId
      ? getAlternative(state, decisionId, alternativeId) || defaultAlternative
      : defaultAlternative
  }
}

const mapDispatchToProps = dispatch => ({
  loadDecisions: bindActionCreators(loadDecisions, dispatch),
  addAlternative: bindActionCreators(addAlternative, dispatch),
  updateAlternative: bindActionCreators(updateAlternative, dispatch),
})

export default connect(mapStateToProps, mapDispatchToProps)(AlternativePage)
