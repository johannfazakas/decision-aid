import * as alternativeApi from "../api/alternativesApi";
import actionType from "./actionType";

export const addAlternative = (decisionId, alternative) => dispatch =>
  alternativeApi
    .addAlternative(decisionId, alternative)
    .then(addedAlternative => dispatch({
      type: actionType.ADD_ALTERNATIVE,
      decisionId,
      alternative: addedAlternative
    }))
    .catch(error => {
      throw error
    });

export const updateAlternative = (decisionId, alternative) => dispatch =>
  alternativeApi
    .updateAlternative(decisionId, alternative)
    .then(updatedAlternative => dispatch({
      type: actionType.UPDATE_ALTERNATIVE,
      decisionId,
      alternative: updatedAlternative
    }))
    .catch(error => {
      throw error
    })

export const deleteAlternative = (decisionId, alternativeId) => dispatch =>
  alternativeApi
    .deleteAlternative(decisionId, alternativeId)
    .then(() => dispatch({
      type: actionType.DELETE_ALTERNATIVE,
      decisionId,
      criteriaId: alternativeId
    }))
    .catch(error => {
      throw error
    });