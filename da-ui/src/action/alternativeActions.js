import * as alternativeApi from "../api/alternativesApi";
import actionType from "./actionType";

export const deleteAlternative = (decisionId, alternativeId) => dispatch =>
  alternativeApi
    .deleteAlternative(decisionId, alternativeId)
    .then(() => dispatch({
      type: actionType.DELETE_ALTERNATIVE,
      decisionId: decisionId,
      criteriaId: alternativeId
    }))
    .catch(error => {
      throw error
    });