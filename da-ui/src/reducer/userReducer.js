import actionType from "../action/actionType";
import initialState from "../store/initialState";

const userReducer = (state = initialState.user, action) => {
  switch (action.type) {

    case actionType.LOGIN_USER:
      return {
        ...state,
        ...action.user
      };

    default:
      return state;
  }
};

export default userReducer;
