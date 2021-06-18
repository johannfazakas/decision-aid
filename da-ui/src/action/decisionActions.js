import actionType from "./actionType";
import * as decisionApi from "../api/decisionApi";

export const loadDecisions = token => dispatch =>
  decisionApi
    .fetchDecisions(token)
    .then(decisions => dispatch({
      type: actionType.LOAD_DECISIONS,
      decisions: decisions.items
    }))
    .catch(error => {
      throw error
    });

export const getDecision = (decisionId, token) => dispatch =>
  decisionApi
    .getDecision(decisionId, token)
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision
    }))
    .catch(error => {
      throw error
    })

export const addDecision = (decision, token) => dispatch =>
  decisionApi.createDecision(decision, token)
    .then(addedDecision => dispatch({
      type: actionType.ADD_DECISION,
      decision: addedDecision
    }))
    .catch(error => {
      throw error
    })

export const updateDecision = (decision, token) => dispatch =>
  decisionApi.updateDecision(decision, token)
    .then(updatedDecision => dispatch({
      type: actionType.UPDATE_DECISION,
      decision: updatedDecision
    }))
    .catch(error => {
      throw error
    })

export const deleteDecision = (decisionId, token) => dispatch =>
  decisionApi
    .deleteDecision(decisionId, token)
    .then(() => dispatch({
      type: actionType.DELETE_DECISION,
      decisionId: decisionId
    }))
    .catch(error => {
      throw error
    });
