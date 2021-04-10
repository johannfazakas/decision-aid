package ro.johann.dm.decision.transfer

import ro.johann.dm.decision.domain.Criteria
import java.util.UUID

data class CriteriaOutput(
  val id: UUID,
  val name: String,
  val weight: Int
) {
  constructor(criteria: Criteria): this(
    id = criteria.id,
    name = criteria.name,
    weight = criteria.weight
  )
}