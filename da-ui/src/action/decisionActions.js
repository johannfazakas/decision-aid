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

export const getDecision = decisionId => dispatch =>
  decisionApi
    .getDecision(decisionId)
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision
    }))
    .catch(error => {
      throw error
    })

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
