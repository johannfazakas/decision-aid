import { handleResponse, handleError } from "./apiUtils";

const baseUrl = "http://localhost:7032/decision/v1/decisions";

export function getDecisions() {
  return fetch(baseUrl)
    .then(handleResponse)
    .catch(handleError);
}
