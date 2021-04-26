import React, { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import { connect } from "react-redux"
import { bindActionCreators } from "redux"
import * as PropTypes from "prop-types"

import { defaultDecision } from "../../../store/default"
import { aidDecision, deleteDecision, loadDecisions, resetDecision } from "../../../action/decisionActions"
import { deleteCriteria } from "../../../action/criteriaActions"
import { deleteAlternative } from "../../../action/alternativeActions"

import DecisionTable from "./table/DecisionTable"
import Warnings from "./table/Warnings"

const DecisionDetailsPage = props => {

  const [decision, setDecision] = useState(props.decision)
  const [warning, setWarning] = useState("")

  useEffect(() => {
    if (props.decisions.length === 0) {
      props.loadDecisions()
        .catch(error => alert("Loading decisions failed. " + error))
    } else {
      setDecision(props.decision)
    }

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
    } else {
      props.history.push("/decision/" + decision.id + "/criteria")
    }
  }

  const handleAddAlternative = () => {
    if (props.decision.status === "processed") {
      setWarning("Reset if you want to add new alternatives.")
    } else {
      props.history.push("/decision/" + decision.id + "/alternative")
    }
  }

  const handleUpdateAlternative = alternativeId => {
    debugger;
    if (props.decision.status === "processed") {
      setWarning("Reset if you want to update alternatives")
    } else {
      props.history.push("/decision/" + decision.id + "/alternative/" + alternativeId)
    }
  }

  const handleUpdateCriteria = criteriaId => {
    if (props.decision.status === "processed") {
      setWarning("Reset if you want to update criteria")
    } else {
      props.history.push("/decision/" + decision.id + "/criteria/" + criteriaId)
    }
  }

  const handleDeleteCriteria = criteriaId => {
    if (props.decision.status === "processed") {
      setWarning("Reset if you want to delete criteria.")
    } else {
      props.deleteCriteria(props.decision.id, criteriaId)
    }
  }

  const handleDeleteAlternative = alternativeId => {
    if (props.decision.status === "processed") {
      setWarning("Reset if you want to delete alternatives.")
    } else {
      props.deleteAlternative(props.decision.id, alternativeId)
    }
  }

  const handleAid = () => {
    if (props.decision.criteria.length === 0)
      setWarning("No criteria defined!")
    else if (props.decision.alternatives.length === 0)
      setWarning("No alternative defined!")
    else if (props.decision.properties.length < props.decision.alternatives.length * props.decision.criteria.length)
      setWarning("All the properties should be set!")
    else if (props.decision.criteria.map(c => c.weight).reduce((sum, w) => sum + w, 0) !== 100)
      setWarning("Criteria weight sum should be 100!")
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
      {/* TODO extract components*/}
      <h1>{decision.name}</h1>
      <h5>{decision.description}</h5>
      <Link to={"/decision/" + decision.id} className="btn btn-dark m-1">Update</Link>
      <div className="btn btn-danger m-1" onClick={handleDelete}>Delete</div>
      <DecisionTable
        decision={decision}
        readOnly={decision.status === "processed"}
        onAddCriteria={handleAddCriteria}
        onAddAlternative={handleAddAlternative}
        onUpdateCriteria={handleUpdateCriteria}
        onUpdateAlternative={handleUpdateAlternative}
        onDeleteCriteria={handleDeleteCriteria}
        onDeleteAlternative={handleDeleteAlternative}
        onAid={handleAid}
        onReset={handleReset}
      />
      <Warnings warning={warning} />
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
