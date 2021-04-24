import actionType from "../action/actionType";
import initialState from "../store/initialState";

const decisionReducer = (state = initialState.decisions, action) => {
  switch (action.type) {

    case actionType.LOAD_DECISIONS:
      return action.decisions;

    case actionType.DELETE_DECISION:
      return state.filter(decision => decision.id !== action.decisionId);

    default:
      return state;
  }
};

export default decisionReducer;
