import React, { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import { connect } from "react-redux"
import { bindActionCreators } from "redux"
import * as PropTypes from "prop-types"

import { defaultDecision } from "../../../store/default"
import { deleteDecision, loadDecisions } from "../../../action/decisionActions"
import { deleteCriteria } from "../../../action/criteriaActions"
import { deleteAlternative } from "../../../action/alternativeActions"

import DecisionTable from "./DecisionTable"

const DecisionDetailsPage = props => {

  const [decision, setDecision] = useState(props.decision)

  useEffect(() => {
    if (props.decisions.length === 0) {
      props.loadDecisions()
        .catch(error => alert("Loading decisions failed. " + error))
    } else {
      setDecision(props.decision)
    }
  }, [props.decision])

  const handleDelete = () => {
    props.deleteDecision(decision.id)
      .then(() => props.history.push("/decisions"))
      .catch(error => alert("Delete decision failed. " + error))
  }

  const handleAddCriteria = () => {
    props.history.push("/decision/" + decision.id + "/criteria")
  }

  const handleAddAlternative = () => {
    props.history.push("/decision/" + decision.id + "/alternative")
  }

  return (
    <div className="jumbotron">
      <h1>{decision.name}</h1>
      <h5>{decision.description}</h5>
      <Link to={"/decision/" + decision.id} className="btn btn-dark m-1">Update</Link>
      <div className="btn btn-danger m-1" onClick={handleDelete}>Delete</div>
      <DecisionTable
        decision={decision}
        onAddCriteria={handleAddCriteria}
        onAddAlternative={handleAddAlternative}
        onDeleteAlternative={props.deleteAlternative}
        onDeleteCriteria={props.deleteCriteria}
      />
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
})

export default connect(mapStateToProps, mapDispatchToProps)(DecisionDetailsPage)
