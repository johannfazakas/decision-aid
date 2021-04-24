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

    case actionType.ADD_CRITERIA:
      return {
        ...state,
        [action.decisionId]: {
          ...state[action.decisionId],
          criteria: [...state[action.decisionId].criteria, action.criteria]
        }
      }

    case actionType.UPDATE_CRITERIA:
      return {
        ...state,
        [action.decisionId]: {
          ...state[action.decisionId],
          criteria: state[action.decisionId].criteria
            .map(criteria => criteria.id === action.criteria.id ? action.criteria : criteria)
        }
      }

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

export const getCriterion = (state, decisionId, criteriaId) => {
  const decision = state.decisions[decisionId]
  if (decision)
    return decision.criteria.find(criteria => criteria.id === criteriaId) || null
  return null
}

export default decisionReducer;
