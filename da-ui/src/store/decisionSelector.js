export const getCriterion = (state, decisionId, criteriaId) => {
  const decision = state.decisions[decisionId]
  return decision ? decision.criteria
    .find(criteria => criteria.id === criteriaId) || null : null
}

export const getAlternative = (state, decisionId, alternativeId) => {
  const decision = state.decisions[decisionId]
  return decision ? decision.alternatives
    .find(alternative => alternative.id === alternativeId) || null : null
}
