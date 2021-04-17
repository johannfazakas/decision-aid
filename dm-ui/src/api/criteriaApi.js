import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const addCriteriaUrl = baseUrl + "/decisions/{decisionId}/criteria";
const deleteCriteriaUrl = baseUrl + "/decisions/{decisionId}/criteria/{criteriaId}";

export const addCriteria = (decisionId, criteria) => {
  const url = addCriteriaUrl
    .replace("{decisionId}", decisionId);
  return fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...criteria})
  })
    .then(handleResponse)
    .catch(handleError)
};

export const deleteCriteria = (decisionId, criteriaId) => {
  const url = deleteCriteriaUrl
    .replace("{decisionId}", decisionId)
    .replace("{criteriaId}", criteriaId)
  return fetch(url, {
    method: "DELETE"
  })
    .then(handleResponse)
    .catch(handleError)
}