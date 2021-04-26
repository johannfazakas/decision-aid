import React, { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import { connect } from "react-redux"
import { bindActionCreators } from "redux"
import * as PropTypes from "prop-types"

import { defaultDecision } from "../../../store/default"
import { aidDecision, resetDecision, deleteDecision, loadDecisions } from "../../../action/decisionActions"
import { deleteCriteria } from "../../../action/criteriaActions"
import { deleteAlternative } from "../../../action/alternativeActions"

import DecisionTable from "./table/DecisionTable"

const DecisionDetailsPage = props => {

  const [decision, setDecision] = useState(props.decision)

  const [warning, setWarning] = useState("")

  const [aidWarning, setAidWarning] = useState("")
  const [showAidWarning, setShowAidWarning] = useState(false)

  useEffect(() => {
    if (props.decisions.length === 0) {
      props.loadDecisions()
        .catch(error => alert("Loading decisions failed. " + error))
    } else {
      setDecision(props.decision)
    }

    if (props.decision.criteria.length === 0)
      setAidWarning("No criteria defined!")
    else if (props.decision.alternatives.length === 0)
      setAidWarning("No alternative defined!")
    else if (props.decision.properties.length < props.decision.alternatives.length * props.decision.criteria.length)
      setAidWarning("All the properties should be set!")
    else if (props.decision.criteria.map(c => c.weight).reduce((sum, w) => sum + w, 0) !== 100)
      setAidWarning("Criteria weight sum should be 100!")
    else
      setAidWarning("")

    setShowAidWarning(false)
    setWarning("")
  }, [props.decision])


  const handleDelete = () => {
    props.deleteDecision(decision.id)
      .then(() => props.history.push("/decisions"))
      .catch(error => alert("Delete decision failed. " + error))
  }

  const handleAddCriteria = () => {
    if (props.decision.status === "processed") {
      setWarning("Reset if you want to add new criteria.")
      return;
    }
    props.history.push("/decision/" + decision.id + "/criteria")
  }

  const handleAddAlternative = () => {
    if (props.decision.status === "processed") {
      setWarning("Reset if you want to add new alternatives.")
      return;
    }
    props.history.push("/decision/" + decision.id + "/alternative")
  }

  const handleAid = () => {
    if (aidWarning !== "")
      setShowAidWarning(true)
    else
      props.aidDecision(decision.id)
        .catch(error => alert("Aid decision failed. " + error))
  }

  const handleReset = () => {
    props.resetDecision(decision.id)
      .catch(error => alert("Reset decision failed. " + error))
  }

  return (
    <div className="jumbotron">
      {/*TODO extract components. commands, links, warning*/}
      <h1>{decision.name}</h1>
      <h5>{decision.description}</h5>
      <Link to={"/decision/" + decision.id} className="btn btn-dark m-1">Update</Link>
      <div className="btn btn-danger m-1" onClick={handleDelete}>Delete</div>
      <DecisionTable
        decision={decision}
        aidWarning={aidWarning}
        onAddCriteria={handleAddCriteria}
        onAddAlternative={handleAddAlternative}
        onAid={handleAid}
        onReset={handleReset}
        onDeleteAlternative={props.deleteAlternative}
        onDeleteCriteria={props.deleteCriteria}
      />
      {showAidWarning && <div className="text-danger text-center">{aidWarning}</div>}
      {warning !== "" && <div className="text-danger text-center">{warning}</div>}
    </div>
  )
}

DecisionDetailsPage.propTypes = {
  decisions: PropTypes.array.isRequired,
  decision: PropTypes.object.isRequired,
  loadDecisions: PropTypes.func.isRequired,
  deleteDecision: PropTypes.func.isRequired,
  deleteCriteria: PropTypes.func.isRequired,
  deleteAlternative: PropTypes.func.isRequired,
  aidDecision: PropTypes.func.isRequired,
  resetDecision: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired,
}

const mapStateToProps = (state, props) => ({
  decisions: Object.values(state.decisions),
  decision: state.decisions[props.match.params.decisionId] || defaultDecision
})

const mapDispatchToProps = dispatch => ({
  loadDecisions: bindActionCreators(loadDecisions, dispatch),
  deleteDecision: bindActionCreators(deleteDecision, dispatch),
  deleteCriteria: bindActionCreators(deleteCriteria, dispatch),
  deleteAlternative: bindActionCreators(deleteAlternative, dispatch),
  aidDecision: bindActionCreators(aidDecision, dispatch),
  resetDecision: bindActionCreators(resetDecision, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(DecisionDetailsPage)
