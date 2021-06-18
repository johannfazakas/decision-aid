import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/user/v1";
const registerUserUrl = baseUrl + "/users";
const generateTokenUrl = baseUrl + "/tokens";
const deleteTokenUrl = baseUrl + "/tokens/{token}"

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
  return fetch(generateTokenUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({...user})
  })
    .then(handleResponse)
    .catch(handleError);
}

export const logoutUser = token => {
  const url = deleteTokenUrl.replace("{token}", token)
  return fetch(url, {
    method: "DELETE"
  })
    .then(handleResponse)
    .catch(handleError);
}