import { generateAuthorizationHeader, handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const getDecisionsUrl = baseUrl + "/decisions/{decisionId}?aid=true";
const listDecisionsUrl = baseUrl + "/decisions?aid=true";
const createDecisionUrl = baseUrl + "/decisions";
const patchDecisionUrl = baseUrl + "/decisions/{decisionId}";
const deleteDecisionUrl = baseUrl + "/decisions/{decisionId}";

export const getDecision = (decisionId, token) => {
  const url = getDecisionsUrl
    .replace("{decisionId}", decisionId);
  return fetch(url, {
    method: "GET",
    headers: {
      "Authorization": generateAuthorizationHeader(token)
    }
  })
    .then(handleResponse)
    .catch(handleError);
};

export const fetchDecisions = token => {
  return fetch(listDecisionsUrl, {
    method: "GET",
    headers: {
      "Authorization": generateAuthorizationHeader(token)
    }
  })
    .then(handleResponse)
    .catch(handleError);
};

export const createDecision = (decision, token) => {
  return fetch(createDecisionUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": generateAuthorizationHeader(token)
    },
    body: JSON.stringify({...decision})
  })
    .then(handleResponse)
    .catch(handleError);
};

export const updateDecision = (decision, token) => {
  const {id, ...body} = decision;
  const url = patchDecisionUrl
    .replace("{decisionId}", id);
  return fetch(url, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
      "Authorization": generateAuthorizationHeader(token)
    },
    body: JSON.stringify(body)
  })
    .then(handleResponse)
    .catch(handleError);
}

export const deleteDecision = (decisionId, token) => {
  const url = deleteDecisionUrl
    .replace("{decisionId}", decisionId);
  return fetch(url, {
    method: "DELETE",
    headers: {
      "Authorization": generateAuthorizationHeader(token)
    }
  })
    .then(handleResponse)
    .catch(handleError);
}
