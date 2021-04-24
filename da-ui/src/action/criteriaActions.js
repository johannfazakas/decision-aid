import * as criteriaApi from "../api/criteriaApi";
import actionType from "./actionType";

export const addCriteria = (decisionId, criteria) => dispatch =>
  criteriaApi
    .addCriteria(decisionId, criteria)
    .then(addedCriteria => dispatch({
      type: actionType.ADD_CRITERIA,
      decisionId,
      criteria: addedCriteria
    }))
    .catch(error => {
      throw error
    });

export const updateCriteria = (decisionId, criteria) => dispatch =>
  criteriaApi
    .updateCriteria(decisionId, criteria)
    .then(updateCriteria => dispatch({
      type: actionType.UPDATE_CRITERIA,
      decisionId,
      criteria: updateCriteria
    }))
    .catch(error => {
      throw error
    })

export const deleteCriteria = (decisionId, criteriaId) => dispatch =>
  criteriaApi
    .deleteCriteria(decisionId, criteriaId)
    .then(() => dispatch({
      type: actionType.DELETE_CRITERIA,
      decisionId,
      criteriaId: criteriaId
    }))
    .catch(error => {
      throw error
    });