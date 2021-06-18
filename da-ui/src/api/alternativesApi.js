import { generateAuthorizationHeader, handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const alternativesUrl = baseUrl + "/decisions/{decisionId}/alternatives";
const alternativeByIdUrl = baseUrl + "/decisions/{decisionId}/alternatives/{alternativeId}"

export const addAlternative = (decisionId, alternative, token) => {
  return fetch(getAlternativesUrl(decisionId), {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": generateAuthorizationHeader(token)
    },
    body: JSON.stringify({...alternative})
  })
    .then(handleResponse)
    .catch(handleError);
};

export const updateAlternative = (decisionId, alternative, token) => {
  const {id, ...body} = alternative;
  return fetch(getAlternativeByIdUrl(decisionId, id), {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
      "Authorization": generateAuthorizationHeader(token)
    },
    body: JSON.stringify(body)
  })
    .then(handleResponse)
    .catch(handleError);
};

export const deleteAlternative = (decisionId, alternativeId, token) => {
  return fetch(getAlternativeByIdUrl(decisionId, alternativeId), {
    method: "DELETE",
    headers: {
      "Authorization": generateAuthorizationHeader(token)
    }
  })
    .then(handleResponse)
    .catch(handleError)
}

const getAlternativesUrl = decisionId => alternativesUrl
  .replace("{decisionId}", decisionId)

const getAlternativeByIdUrl = (decisionId, alternativeId) => alternativeByIdUrl
  .replace("{decisionId}", decisionId)
  .replace("{alternativeId}", alternativeId);