import actionType from "../action/actionType";
import initialState from "../store/initialState";

const decisionReducer = (state = initialState.decisions, action) => {
  switch (action.type) {

    case actionType.LOAD_DECISIONS:
      return state.decisions === {} && action.decisions === []
        ? state
        : action.decisions
          .reduce((newState, decision) => ({...newState, [decision.id]: decision}), {});

    case actionType.GET_DECISION:
      return {
        ...state.decisions,
        [action.decision.id]: action.decision
      }

    case actionType.ADD_DECISION:
      return {...state, [action.decision.id]: action.decision}

    case actionType.UPDATE_DECISION:
      return {...state, [action.decision.id]: action.decision}

    default:
      return state;
  }
};

export default decisionReducer;
