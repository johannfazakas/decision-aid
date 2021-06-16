import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/user/v1";
const registerUserUrl = baseUrl + "/users";
const loginUserUrl = baseUrl + "/tokens"

export const registerUser = user => {
  return fetch(registerUserUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...user})
  })
    .then(handleResponse)
    .catch(handleError);
}

export const loginUser = user => {
  return fetch(loginUserUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...user})
  })
    .then(handleResponse)
    .catch(handleError);
}