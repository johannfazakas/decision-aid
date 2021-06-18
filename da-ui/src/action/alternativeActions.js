import * as alternativeApi from "../api/alternativesApi";
import * as propertyApi from "../api/propertyApi";
import * as decisionApi from "../api/decisionApi";
import actionType from "./actionType";

export const addAlternative = (decisionId, alternative, properties, token) => dispatch =>
  alternativeApi
    .addAlternative(decisionId, alternative, token)
    .then(addedAlternative =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        alternativeId: addedAlternative.id
      }, token))))
    .then(() => decisionApi.getDecision(decisionId, token))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    });

export const updateAlternative = (decisionId, alternative, properties, token) => dispatch =>
  alternativeApi
    .updateAlternative(decisionId, alternative, token)
    .then(updatedAlternative =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        alternativeId: updatedAlternative.id
      }, token))))
    .then(() => decisionApi.getDecision(decisionId, token))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    })

export const deleteAlternative = (decisionId, alternativeId, token) => dispatch =>
  alternativeApi
    .deleteAlternative(decisionId, alternativeId, token)
    .then(() => decisionApi.getDecision(decisionId, token))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    });