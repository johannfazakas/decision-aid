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
        .filter(decisionId => decisionId !== action.decisionId)
        .reduce((newState, decisionId) => ({...newState, [decisionId]: state[decisionId]}), {})

    case actionType.DELETE_CRITERIA:
      return {
        ...state,
        [action.decisionId]: {
          ...state[action.decisionId],
          criteria: state[action.decisionId].criteria
            .filter(criteria => criteria.id !== action.criteriaId)
        }
      }

    case actionType.DELETE_ALTERNATIVE:
      return {
        ...state,
        [action.decisionId]: {
          ...state[action.decisionId],
          alternatives: state[action.decisionId].alternatives
            .filter(alternative => alternative.id !== action.criteriaId)
        }
      }

    default:
      return state;
  }
};

export default decisionReducer;
