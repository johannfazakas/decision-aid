import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const criteriaUrl = baseUrl + "/decisions/{decisionId}/criteria";
const criteriaByIdUrl = baseUrl + "/decisions/{decisionId}/criteria/{criteriaId}";

export const addCriteria = (decisionId, criteria) => {
  return fetch(getCriteriaUrl(decisionId), {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...criteria})
  })
    .then(handleResponse)
    .catch(handleError);
};

export const updateCriteria = (decisionId, criteria) => {
  const {id, ...body} = criteria;
  return fetch(getCriteriaByIdUrl(decisionId, id), {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...body})
  })
    .then(handleResponse)
    .catch(handleError);
}

export const deleteCriteria = (decisionId, criteriaId) => {
  return fetch(getCriteriaByIdUrl(decisionId, criteriaId), {
    method: "DELETE"
  })
    .then(handleResponse)
    .catch(handleError);
}

const getCriteriaUrl = decisionId => criteriaUrl
  .replace("{decisionId}", decisionId);

const getCriteriaByIdUrl = (decisionId, criteriaId) => criteriaByIdUrl
  .replace("{decisionId}", decisionId)
  .replace("{criteriaId}", criteriaId);