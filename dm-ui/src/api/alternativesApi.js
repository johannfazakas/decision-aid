import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const addAlternativeUrl = baseUrl + "/decisions/{decisionId}/alternatives";

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