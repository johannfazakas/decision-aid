import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const propertiesUrl = baseUrl + "/decisions/{decisionId}/properties";

export const setProperty = (decisionId, property) => {
  return fetch(getPropertyUrl(decisionId), {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...property})
  })
    .then(handleResponse)
    .catch(handleError);
}

const getPropertyUrl = (decisionId) => propertiesUrl
  .replace("{decisionId}", decisionId)
