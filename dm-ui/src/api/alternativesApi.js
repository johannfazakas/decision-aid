import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const addAlternativeUrl = baseUrl + "/decisions/{decisionId}/alternatives";
const deleteAlternativeUrl = baseUrl + "/decisions/{decisionId}/alternatives/{alternativeId}"

export const addAlternative = (decisionId, alternative) => {
  const url = addAlternativeUrl
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

export const deleteAlternative = (decisionId, alternativeId) => {
  const url = deleteAlternativeUrl
    .replace("{decisionId}", decisionId)
    .replace("{alternativeId}", alternativeId)
  return fetch(url, {
    method: "DELETE"
  })
    .then(handleResponse)
    .catch(handleError)
}