package ro.johann.da.decision.api.transfer

import ro.johann.da.decision.domain.Property
import java.util.UUID

data class PropertyOutput(
  val alternativeId: UUID,
  val criteriaId: UUID,
  val value: Double
) {
  constructor(property: Property) : this(
    alternativeId = property.alternative.id,
    criteriaId = property.criteria.id,
    value = property.value
  )
}
