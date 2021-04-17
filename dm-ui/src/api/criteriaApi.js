import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const addCriteriaUrl = baseUrl + "/decisions/{decisionId}/criteria";

export const addCriteria = (decisionId, alternative) => {
  const url = addCriteriaUrl
    .replace("{decisionId}", decisionId);
  return fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...alternative})
  })
    .then(handleResponse)
    .catch(handleError)
};