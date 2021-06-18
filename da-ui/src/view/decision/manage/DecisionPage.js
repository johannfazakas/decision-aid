import React, { useEffect, useState } from "react";
import * as PropTypes from "prop-types";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";

import { addDecision, loadDecisions, updateDecision } from "../../../action/decisionActions";
import { defaultDecision } from "../../../store/default";

import DecisionForm from "./DecisionForm";

const DecisionPage = props => {

  const [decision, setDecision] = useState({...props.decision});
  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (!props.user.token) {
      props.history.push("/")
    }
    if (props.decisions.length === 0) {
      props.loadDecisions(props.user.token)
        .catch(error => alert("Loading decisions failed. " + error))
    } else {
      setDecision({...props.decision})
    }
  }, [props.decision, props.user]);

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
      ? props.updateDecision(decision, props.user.token)
      : props.addDecision(decision, props.user.token)
    saveDecision.then(() => navigateBack())
  }

  return (
    <div className="jumbotron">
      <h2>{decision.id ? "Update" : "Add"} decision</h2>
      <DecisionForm
        decision={decision}
        errors={errors}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancel={navigateBack}
      />
    </div>
  );
};

DecisionPage.propTypes = {
  user: PropTypes.object.isRequired,
  decisions: PropTypes.array.isRequired,
  decision: PropTypes.object.isRequired,
  loadDecisions: PropTypes.func.isRequired,
  addDecision: PropTypes.func.isRequired,
  updateDecision: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired,
}

const mapStateToProps = (state, props) => {
  const decisionId = props.match.params.decisionId;
  return {
    user: state.user,
    decisions: Object.values(state.decisions),
    decision: decisionId
      ? state.decisions[decisionId] || defaultDecision
      : defaultDecision
  }
}

const mapDispatchToProps = dispatch => ({
  loadDecisions: bindActionCreators(loadDecisions, dispatch),
  addDecision: bindActionCreators(addDecision, dispatch),
  updateDecision: bindActionCreators(updateDecision, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(DecisionPage);
