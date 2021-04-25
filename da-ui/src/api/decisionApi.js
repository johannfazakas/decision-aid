import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const getDecisionsUrl = baseUrl + "/decisions/{decisionId}";
const listDecisionsUrl = baseUrl + "/decisions";
const createDecisionUrl = baseUrl + "/decisions";
const patchDecisionUrl = baseUrl + "/decisions/{decisionId}";
const deleteDecisionUrl = baseUrl + "/decisions/{decisionId}";
const aidDecisionUrl = baseUrl + "/decisions/{decisionId}/aid";
const defineDecisionUrl = baseUrl + "/decisions/{decisionId}/define";

export const getDecision = decisionId => {
  const url = getDecisionsUrl
    .replace("{decisionId}", decisionId);
  return fetch(url, {
    method: "GET"
  })
    .then(handleResponse)
    .catch(handleError);
};

export const fetchDecisions = () => {
  return fetch(listDecisionsUrl, {
    method: "GET"
  })
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

export const updateDecision = decision => {
  const {id, ...body} = decision;
  const url = patchDecisionUrl
    .replace("{decisionId}", id);
  return fetch(url, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(body)
  })
    .then(handleResponse)
    .catch(handleError);
}

export const deleteDecision = decisionId => {
  const url = deleteDecisionUrl
    .replace("{decisionId}", decisionId);
  return fetch(url, {
    method: "DELETE"
  })
    .then(handleResponse)
    .catch(handleError);
}

export const aidDecision = decisionId => {
  const url = aidDecisionUrl
    .replace("{decisionId}", decisionId)
  return fetch(url, {
    method: "PUT"
  })
    .then(handleResponse)
    .catch(handleError)
}

export const defineDecision = decisionId => {
  debugger
  const url = defineDecisionUrl
    .replace("{decisionId}", decisionId)
  return fetch(url, {
    method: "PUT"
  })
    .then(handleResponse)
    .catch(handleError)
}