import actionType from "./actionType";
import * as decisionApi from "../api/decisionApi";

export const loadDecisions = () => dispatch =>
  decisionApi
    .fetchDecisions()
    .then(response => dispatch({
      type: actionType.LOAD_DECISIONS,
      decisions: response.items
    }))
    .catch(error => {
      throw error
    });

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