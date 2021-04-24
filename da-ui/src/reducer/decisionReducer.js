import actionType from "../action/actionType";
import initialState from "../store/initialState";

const decisionReducer = (state = initialState.decisions, action) => {
  switch (action.type) {

    case actionType.LOAD_DECISIONS:
      return action.decisions
        .reduce((newState, decision) => ({...newState, [decision.id]: decision}), {})

    case actionType.ADD_DECISION:
      return {...state, [action.decision.id]: action.decision}

    case actionType.UPDATE_DECISION:
      return {...state, [action.decision.id]: action.decision}

    case actionType.DELETE_DECISION:
      return Object.keys(state)
        .filter(decisionId => decisionId !== String(action.decisionId))
        .reduce((newState, decisionId) => ({...newState, [decisionId]: state[decisionId]}), {})

    default:
      return state;
  }
};

export default decisionReducer;
