import React, { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import { connect } from "react-redux"
import { bindActionCreators } from "redux"
import * as PropTypes from "prop-types"

import { defaultDecision } from "../../../store/default"
import { deleteDecision, loadDecisions } from "../../../action/decisionActions"
import { deleteCriteria } from "../../../action/criteriaActions"
import { deleteAlternative } from "../../../action/alternativeActions"

import DecisionTable from "./table/DecisionTable"

const DecisionDetailsPage = props => {

  const [decision, setDecision] = useState(props.decision)

  useEffect(() => {
    if (!props.user.token) {
      props.history.push("/")
    }
    if (props.decisions.length === 0) {
      props.loadDecisions(props.user.token)
        .catch(error => alert("Loading decisions failed. " + error))
    } else {
      setDecision(props.decision)
    }
  }, [props.decision, props.user])


  const handleDelete = () => {
    props.deleteDecision(decision.id, props.user.token)
      .then(() => props.history.push("/decisions"))
      .catch(error => alert("Delete decision failed. " + error))
  }

  const handleAddCriteria = () => props.history.push("/decision/" + decision.id + "/criteria")

  const handleAddAlternative = () => props.history.push("/decision/" + decision.id + "/alternative")

  const handleUpdateAlternative = alternativeId =>
    props.history.push("/decision/" + decision.id + "/alternative/" + alternativeId)

  const handleUpdateCriteria = criteriaId => props.history.push("/decision/" + decision.id + "/criteria/" + criteriaId)

  const handleDeleteCriteria = criteriaId => props.deleteCriteria(props.decision.id, criteriaId, props.user.token)

  const handleDeleteAlternative = alternativeId => props.deleteAlternative(props.decision.id, alternativeId, props.user.token)

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
        onUpdateCriteria={handleUpdateCriteria}
        onUpdateAlternative={handleUpdateAlternative}
        onDeleteCriteria={handleDeleteCriteria}
        onDeleteAlternative={handleDeleteAlternative}
      />
    </div>
  )
}

DecisionDetailsPage.propTypes = {
  user: PropTypes.object.isRequired,
  decisions: PropTypes.array.isRequired,
  decision: PropTypes.object.isRequired,
  loadDecisions: PropTypes.func.isRequired,
  deleteDecision: PropTypes.func.isRequired,
  deleteCriteria: PropTypes.func.isRequired,
  deleteAlternative: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired,
}

const mapStateToProps = (state, props) => ({
  user: state.user,
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
