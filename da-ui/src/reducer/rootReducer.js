import { combineReducers } from "redux";
import decisionReducer from "./decisionReducer";
import userReducer from "./userReducer";

const rootReducer = combineReducers({
  decisions: decisionReducer,
  user: userReducer
});

export default rootReducer;