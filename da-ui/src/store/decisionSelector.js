export const getCriteriaById = (state, decisionId, criteriaId) => {
  const decision = state.decisions[decisionId]
  return decision ? decision.criteria
    .find(criteria => criteria.id === criteriaId) || null : null
}

export const getCriteria = (state, decisionId) => {
  const decision = state.decisions[decisionId]
  return decision ? decision.criteria || [] : []
}

export const getAlternativeById = (state, decisionId, alternativeId) => {
  const decision = state.decisions[decisionId]
  return decision ? decision.alternatives
    .find(alternative => alternative.id === alternativeId) || null : null
}

export const getAlternatives = (state, decisionId) => {
  const decision = state.decisions[decisionId]
  return decision ? decision.alternatives || [] : []
}


export const getProperty = (state, decisionId, criteriaId, alternativeId) => {
  const decision = state.decisions[decisionId]
  return decision ? decision.properties
    .find(property => property.criteriaId === criteriaId && property.alternativeId === alternativeId) || null : null
}