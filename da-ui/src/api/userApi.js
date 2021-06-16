import { handleError, handleResponse } from "./apiUtils";

const baseUrl = "http://localhost:7049/user/v1";
const registerUserUrl = baseUrl + "/users";

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