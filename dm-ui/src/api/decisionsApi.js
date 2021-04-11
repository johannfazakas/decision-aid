import { handleResponse, handleError } from "./apiUtils";

const baseUrl = "http://localhost:7049/decision/v1/decisions";

export function getDecisions() {
  return fetch(baseUrl)
    .then(handleResponse)
    .catch(handleError);
}
