import actionType from "./actionType";
import * as decisionApi from "../api/decisionApi";

export const loadDecisions = () => dispatch =>
  decisionApi
    .fetchDecisions()
    .then(decisions => dispatch({
      type: actionType.LOAD_DECISIONS,
      decisions: decisions.items
    }))
    .catch(error => {
      throw error
    });

export const getDecision = decisionId => dispatch => {
  return decisionApi
    .getDecision(decisionId)
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision
    }))
    .catch(error => {
      throw error
    });
}

export const addDecision = decision => dispatch =>
  decisionApi.createDecision(decision)
    .then(addedDecision => dispatch({
      type: actionType.ADD_DECISION,
      decision: addedDecision
    }))
    .catch(error => {
      throw error
    })

export const updateDecision = decision => dispatch =>
  decisionApi.updateDecision(decision)
    .then(updatedDecision => dispatch({
      type: actionType.UPDATE_DECISION,
      decision: updatedDecision
    }))
    .catch(error => {
      throw error
    })

export const deleteDecision = decisionId => dispatch =>
  decisionApi
    .deleteDecision(decisionId)
    .then(() => dispatch({
      type: actionType.DELETE_DECISION,
      decisionId: decisionId
    }))
    .catch(error => {
      throw error
    });

export const aidDecision = decisionId => dispatch =>
  decisionApi
    .aidDecision(decisionId)
    .then(decision => dispatch({
      type: actionType.AID_DECISION,
      decision
    }))
    .catch(error => {
      throw error
    })

export const resetDecision = decisionId => dispatch =>
  decisionApi
    .resetDecision(decisionId)
    .then(decision => dispatch({
      type: actionType.RESET_DECISION,
      decision
    }))
    .catch(error => {
      throw error
    })