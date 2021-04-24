import React, { useEffect, useState } from "react";
import * as PropTypes from "prop-types";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";

import * as decisionActions from "../../../action/decisionActions"

import DecisionForm from "./DecisionForm";

const DecisionPage = props => {

  const [decision, setDecision] = useState({...props.decision});
  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (props.decisions.length === 0) {
      props.loadDecisions()
        .catch(error => alert("Loading courses failed." + error))
    } else {
      setDecision({...props.decision})
    }
  }, [props.decision]);

  const navigateBack = () => decision.id
    ? props.history.push("/decision/" + decision.id + "/details")
    : props.history.push("/decisions")

  const validateForm = () => {
    const _errors = {};
    if (!decision.name) _errors.name = "Name is required";
    setErrors(_errors);
    return Object.keys(_errors).length === 0;
  }

  const handleChange = event => {
    const {name, value} = event.target
    setDecision(it => ({...it, [name]: value}))
  }

  const handleSubmit = event => {
    event.preventDefault()
    if (!validateForm()) return;
    const saveDecision = decision.id
      ? props.updateDecision(decision)
      : props.addDecision(decision)
    saveDecision.then(() => navigateBack())
  }

  const handleCancel = navigateBack;

  return (
    <div className="jumbotron">
      <h2>{decision.id ? "Update" : "Add"} decision</h2>
      <DecisionForm
        decision={decision}
        errors={errors}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancel={handleCancel}
      />
    </div>
  );
};

DecisionPage.propTypes = {
  decisions: PropTypes.array.isRequired,
  decision: PropTypes.object.isRequired,
  loadDecisions: PropTypes.func.isRequired,
  addDecision: PropTypes.func.isRequired,
  updateDecision: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired,
}

const mapStateToProps = (state, props) => {
  const decisionId = props.match.params.decisionId;
  const defaultDecisionSupplier = () => ({
    id: null,
    name: "",
    description: "",
  })
  return {
    decisions: state.decisions,
    decision: decisionId
      ? state.decisions.find(decision => decision.id === decisionId) || defaultDecisionSupplier()
      : defaultDecisionSupplier()
  }
}

const mapDispatchToProps = dispatch => ({
  loadDecisions: bindActionCreators(decisionActions.loadDecisions, dispatch),
  addDecision: bindActionCreators(decisionActions.addDecision, dispatch),
  updateDecision: bindActionCreators(decisionActions.updateDecision, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(DecisionPage);
