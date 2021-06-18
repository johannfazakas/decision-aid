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

export const loginUser = user => dispatch =>
  userApi.loginUser(user)
    .then(token => dispatch({
      type: actionType.LOGIN_USER,
      user: {
        id: token.userId,
        email: user.email,
        token: token.token
      }
    }))
    .catch(error => {
      throw error
    })

export const logoutUser = user => dispatch =>
  userApi.logoutUser(user.token)
    .then(() => dispatch({
      type: actionType.LOGOUT_USER
    }))
    .catch(() => dispatch({
      type: actionType.LOGOUT_USER
    }));
