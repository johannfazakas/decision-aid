import * as criteriaApi from "../api/criteriaApi";
import * as propertyApi from "../api/propertyApi";
import * as decisionApi from "../api/decisionApi";
import actionType from "./actionType";

export const addCriteria = (decisionId, criteria, properties, token) => dispatch =>
  criteriaApi
    .addCriteria(decisionId, criteria, token)
    .then(addedCriteria =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        criteriaId: addedCriteria.id
      }, token))))
    .then(() => decisionApi.getDecision(decisionId, token))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    });

export const updateCriteria = (decisionId, criteria, properties, token) => dispatch =>
  criteriaApi
    .updateCriteria(decisionId, criteria, token)
    .then(updatedCriteria =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        criteriaId: updatedCriteria.id
      }, token))))
    .then(() => decisionApi.getDecision(decisionId, token))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    })

export const deleteCriteria = (decisionId, criteriaId, token) => dispatch =>
  criteriaApi
    .deleteCriteria(decisionId, criteriaId, token)
    .then(() => decisionApi.getDecision(decisionId, token))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    });