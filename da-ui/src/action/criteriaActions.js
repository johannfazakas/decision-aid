import * as criteriaApi from "../api/criteriaApi";
import * as propertyApi from "../api/propertyApi";
import * as decisionApi from "../api/decisionApi";
import actionType from "./actionType";

export const addCriteria = (decisionId, criteria, properties) => dispatch =>
  criteriaApi
    .addCriteria(decisionId, criteria)
    .then(addedCriteria =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        criteriaId: addedCriteria.id
      }))))
    .then(() => decisionApi.getDecision(decisionId))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    });

export const updateCriteria = (decisionId, criteria, properties) => dispatch =>
  criteriaApi
    .updateCriteria(decisionId, criteria)
    .then(updatedCriteria =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        criteriaId: updatedCriteria.id
      }))))
    .then(() => decisionApi.getDecision(decisionId))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    })

export const deleteCriteria = (decisionId, criteriaId) => dispatch =>
  criteriaApi
    .deleteCriteria(decisionId, criteriaId)
    .then(() => decisionApi.getDecision(decisionId))
    .then(decision => dispatch({
      type: actionType.GET_DECISION,
      decision: decision
    }))
    .catch(error => {
      throw error
    });