package ro.johann.dm.decision.transfer

import ro.johann.dm.decision.domain.Decision
import java.util.UUID

data class DecisionTO (
  val id: UUID?,
  val name: String,
  val description: String?,
  val criteria: List<CriteriaTO> = emptyList(),
  val alternatives: List<AlternativeTO> = emptyList()
) {
  constructor(
    decision: Decision
  ): this(
    id = decision.id,
    name = decision.name,
    description = decision.description
  )
}
