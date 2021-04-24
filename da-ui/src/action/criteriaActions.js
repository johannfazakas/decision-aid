import * as criteriaApi from "../api/criteriaApi";
import actionType from "./actionType";

export const deleteCriteria = (decisionId, criteriaId) => dispatch =>
  criteriaApi
    .deleteCriteria(decisionId, criteriaId)
    .then(() => dispatch({
      type: actionType.DELETE_CRITERIA,
      decisionId: decisionId,
      criteriaId: criteriaId
    }))
    .catch(error => {
      throw error
    });