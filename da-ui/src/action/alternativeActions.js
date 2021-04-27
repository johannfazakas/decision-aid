import * as alternativeApi from "../api/alternativesApi";
import * as propertyApi from "../api/propertyApi";
import * as decisionApi from "../api/decisionApi";
import actionType from "./actionType";

export const addAlternative = (decisionId, alternative, properties) => dispatch =>
  alternativeApi
    .addAlternative(decisionId, alternative)
    .then(addedAlternative =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        alternativeId: addedAlternative.id
      }))))
    .then(() => decisionApi.getDecision(decisionId))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
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
      }))))
    .then(() => decisionApi.getDecision(decisionId))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    })

export const deleteAlternative = (decisionId, alternativeId) => dispatch =>
  alternativeApi
    .deleteAlternative(decisionId, alternativeId)
    .then(() => decisionApi.getDecision(decisionId))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    });