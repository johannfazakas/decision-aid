import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const getDecisionsUrl = baseUrl + "/decisions";
const createDecisionUrl = baseUrl + "/decisions";

export const getDecisions = () => {
  return fetch(getDecisionsUrl)
    .then(handleResponse)
    .catch(handleError);
};

export const createDecision = decision => {
  return fetch(createDecisionUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...decision})
  })
    .then(handleResponse)
    .catch(handleError);
};