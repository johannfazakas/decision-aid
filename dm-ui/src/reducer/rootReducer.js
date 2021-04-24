import { combineReducers } from "redux";
import decisionReducer from "./decisionReducer";

const rootReducer = combineReducers({
  decisions: decisionReducer
});

export default rootReducer;