import dispatcher from "../dispatcher/appDispatcher";
import actionType from "./actionType";
import * as decisionApi from "../api/decisionApi";

export const loadDecisions = () => {
  return decisionApi.fetchDecisions()
    .then(response => {
      dispatcher.dispatch({
        actionType: actionType.LOAD_DECISIONS,
        decisions: response.items
      });
    });
};

export const deleteDecision = decisionId => {
  return decisionApi.deleteDecision(decisionId)
    .then(() => {
      dispatcher.dispatch({
        actionType: actionType.DELETE_DECISION,
        decisionId
      });
    });
}