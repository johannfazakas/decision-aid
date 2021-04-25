import actionType from "../action/actionType";
import initialState from "../store/initialState";

const decisionReducer = (state = initialState.decisions, action) => {
  switch (action.type) {

    case actionType.LOAD_DECISIONS:
      return state.decisions === {} && action.decisions === []
        ? state
        : action.decisions
          .reduce((newState, decision) => ({...newState, [decision.id]: decision}), {});

    case actionType.ADD_DECISION:
      return {...state, [action.decision.id]: action.decision}

    case actionType.UPDATE_DECISION:
    case actionType.AID_DECISION:
    case actionType.RESET_DECISION:
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
          criteria: [...state[action.decisionId].criteria, action.criteria],
          properties: [
            ...state[action.decisionId].properties
              .filter(property => !(property.criteriaId === action.criteria.id
                && action.properties.find(p => p.alternativeId === property.alternativeId))),
            ...action.properties
              .map(property => ({...property, criteriaId: action.criteria.id}))
          ]
        }
      }

    case actionType.UPDATE_CRITERIA:
      return {
        ...state,
        [action.decisionId]: {
          ...state[action.decisionId],
          criteria: state[action.decisionId].criteria
            .map(criteria => criteria.id === action.criteria.id ? action.criteria : criteria),
          properties: [
            ...state[action.decisionId].properties
              .filter(property => !(property.criteriaId === action.criteria.id
                && action.properties.find(p => p.alternativeId === property.alternativeId))),
            ...action.properties
              .map(property => ({...property, criteriaId: action.criteria.id}))
          ]
        }
      }

    case actionType.DELETE_CRITERIA:
      return {
        ...state,
        [action.decisionId]: {
          ...state[action.decisionId],
          criteria: state[action.decisionId].criteria
            .filter(criteria => criteria.id !== action.criteriaId),
          properties: state[action.decisionId].properties
            .filter(property => property.criteriaId !== action.criteriaId)
        }
      }

    case actionType.ADD_ALTERNATIVE:
      return {
        ...state,
        [action.decisionId]: {
          ...state[action.decisionId],
          alternatives: [...state[action.decisionId].alternatives, action.alternative],
          properties: [
            ...state[action.decisionId].properties
              .filter(property => !(property.alternativeId === action.alternative.id
                && action.properties.find(p => p.criteriaId === property.criteriaId))),
            ...action.properties
              .map(property => ({...property, alternativeId: action.alternative.id}))
          ]
        }
      }

    case actionType.UPDATE_ALTERNATIVE:
      return {
        ...state,
        [action.decisionId]: {
          ...state[action.decisionId],
          alternatives: state[action.decisionId].alternatives
            .map(alternative => alternative.id === action.alternative.id ? action.alternative : alternative),
          properties: [
            ...state[action.decisionId].properties
              .filter(property => !(property.alternativeId === action.alternative.id
                && action.properties.find(p => p.criteriaId === property.criteriaId))),
            ...action.properties
              .map(property => ({...property, alternativeId: action.alternative.id}))
          ]
        }
      }

    case actionType.DELETE_ALTERNATIVE:
      return {
        ...state,
        [action.decisionId]: {
          ...state[action.decisionId],
          alternatives: state[action.decisionId].alternatives
            .filter(alternative => alternative.id !== action.criteriaId),
          properties: state[action.decisionId].properties
            .filter(property => property.alternativeId !== action.alternativeId)
        }
      }

    default:
      return state;
  }
};

export default decisionReducer;
