import actionType from "./actionType";
import * as userApi from "../api/userApi";

export const registerUser = user => dispatch =>
  userApi.registerUser(user)
    .then(registeredUser => dispatch({
      type: actionType.REGISTER_USER,
      user: registeredUser
    }))
    .catch(error => {
      throw error
    })
