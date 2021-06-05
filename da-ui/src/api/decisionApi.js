import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1";
const getDecisionsUrl = baseUrl + "/decisions/{decisionId}?aid=true";
const listDecisionsUrl = baseUrl + "/decisions?aid=true";
const createDecisionUrl = baseUrl + "/decisions";
const patchDecisionUrl = baseUrl + "/decisions/{decisionId}";
const deleteDecisionUrl = baseUrl + "/decisions/{decisionId}";
const aidDecisionUrl = baseUrl + "/decisions/{decisionId}/aid";
const resetDecisionUrl = baseUrl + "/decisions/{decisionId}/reset";

export const getDecision = decisionId => {
  const url = getDecisionsUrl
    .replace("{decisionId}", decisionId);
  return fetch(url, {
    method: "GET",
    headers: {
      "Authorization": "Bearer 11112222-3333-4444-5555-666677778888"
    }
  })
    .then(handleResponse)
    .catch(handleError);
};

export const fetchDecisions = () => {
  return fetch(listDecisionsUrl, {
    method: "GET",
    headers: {
      "Authorization": "Bearer 11112222-3333-4444-5555-666677778888"
    }
  })
    .then(handleResponse)
    .catch(handleError);
};

export const createDecision = decision => {
  return fetch(createDecisionUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer 11112222-3333-4444-5555-666677778888"
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
      "Content-Type": "application/json",
      "Authorization": "Bearer 11112222-3333-4444-5555-666677778888"
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
    method: "DELETE",
    headers: {
      "Authorization": "Bearer 11112222-3333-4444-5555-666677778888"
    }
  })
    .then(handleResponse)
    .catch(handleError);
}

export const aidDecision = decisionId => {
  const url = aidDecisionUrl
    .replace("{decisionId}", decisionId)
  return fetch(url, {
    method: "PUT",
    headers: {
      "Authorization": "Bearer 11112222-3333-4444-5555-666677778888"
    }
  })
    .then(handleResponse)
    .catch(handleError)
}

export const resetDecision = decisionId => {
  const url = resetDecisionUrl
    .replace("{decisionId}", decisionId)
  return fetch(url, {
    method: "PUT",
    headers: {
      "Authorization": "Bearer 11112222-3333-4444-5555-666677778888"
    }
  })
    .then(handleResponse)
    .catch(handleError)
}