package ro.johann.dm.decision.api.transfer

import ro.johann.dm.decision.domain.Decision
import java.util.UUID

data class DecisionOutput(
  val id: UUID?,
  val name: String?,
  val description: String?,
  val criteria: List<CriteriaOutput> = emptyList(),
  val alternatives: List<AlternativeOutput> = emptyList(),
  val properties: List<PropertyOutput> = emptyList()
) {
  constructor(
    decision: Decision
  ) : this(
    id = decision.id,
    name = decision.name,
    description = decision.description,
    criteria = decision.criteria.map(::CriteriaOutput),
    alternatives = decision.alternatives.map(::AlternativeOutput),
    properties = decision.alternatives
      .asSequence()
      .flatMap { it.properties.asSequence() }
      .map(::PropertyOutput)
      .toList()
  )
}
