import * as criteriaApi from "../api/criteriaApi";
import * as propertyApi from "../api/propertyApi";
import actionType from "./actionType";

export const addCriteria = (decisionId, criteria, properties) => dispatch =>
  criteriaApi
    .addCriteria(decisionId, criteria)
    .then(addedCriteria =>
      Promise.all(properties.map(property => propertyApi.setProperty(decisionId, {
        ...property,
        criteriaId: addedCriteria.id
      })))
        .then(() => dispatch({
          type: actionType.ADD_CRITERIA,
          decisionId,
          criteria: addedCriteria,
          properties
        })))
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
      })))
        .then(() => dispatch({
          type: actionType.UPDATE_CRITERIA,
          decisionId,
          criteria: updatedCriteria,
          properties
        })))
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