import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const propertiesUrl = baseUrl + "/decisions/{decisionId}/alternatives/{alternativeId}/criteria/{criteriaId}/property";

export const setProperty = (decisionId, property) => {
  const {alternativeId, criteriaId, ...body} = property
  return fetch(getPropertyUrl(decisionId, alternativeId, criteriaId), {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...body})
  })
    .then(handleResponse)
    .catch(handleError);
}

const getPropertyUrl = (decisionId, alternativeId, criteriaId) => propertiesUrl
  .replace("{decisionId}", decisionId)
  .replace("{alternativeId}", alternativeId)
  .replace("{criteriaId}", criteriaId)
