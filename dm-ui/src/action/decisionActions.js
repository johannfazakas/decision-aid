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

export const addDecision = decision => dispatch =>
  decisionApi.createDecision(decision)
    .then(() => dispatch({
      type: actionType.ADD_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    })

export const updateDecision = decision => dispatch =>
  decisionApi.updateDecision(decision)
    .then(decision => dispatch({
      type: actionType.UPDATE_DECISION,
      decision: decision
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