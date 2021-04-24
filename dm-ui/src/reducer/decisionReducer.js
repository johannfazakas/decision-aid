import actionType from "../action/actionType";
import initialState from "../store/initialState";

const decisionReducer = (state = initialState.decisions, action) => {
  switch (action.type) {

    case actionType.LOAD_DECISIONS:
      return action.decisions

    case actionType.ADD_DECISION:
      return [...state, action.decision]

    case actionType.UPDATE_DECISION:
      return state.map(decision => decision.id === action.decision.id ? action.decision : decision)

    case actionType.DELETE_DECISION:
      return state.filter(decision => decision.id !== action.decisionId)

    default:
      return state;
  }
};

export default decisionReducer;
