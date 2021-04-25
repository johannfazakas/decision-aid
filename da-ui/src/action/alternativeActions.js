import * as alternativeApi from "../api/alternativesApi";
import * as propertyApi from "../api/propertyApi";
import actionType from "./actionType";

export const addAlternative = (decisionId, alternative, properties) => dispatch =>
  alternativeApi
    .addAlternative(decisionId, alternative)
    .then(addedAlternative =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        alternativeId: addedAlternative.id
      })))
        .then(() => dispatch({
          type: actionType.ADD_ALTERNATIVE,
          decisionId,
          alternative: addedAlternative,
          properties
        })))
    .catch(error => {
      throw error
    });

export const updateAlternative = (decisionId, alternative, properties) => dispatch =>
  alternativeApi
    .updateAlternative(decisionId, alternative)
    .then(updatedAlternative =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        alternativeId: updatedAlternative.id
      })))
        .then(() => dispatch({
          type: actionType.UPDATE_ALTERNATIVE,
          decisionId,
          alternative: updatedAlternative,
          properties
        })))
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