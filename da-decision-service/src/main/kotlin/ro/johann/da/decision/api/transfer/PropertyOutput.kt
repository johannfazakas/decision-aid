package ro.johann.da.decision.api.transfer

import com.fasterxml.jackson.annotation.JsonFormat
import ro.johann.da.decision.domain.Property
import java.time.LocalDateTime
import java.util.UUID

data class PropertyOutput(
  val alternativeId: UUID,
  val criteriaId: UUID,
  val value: Double,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val createdAt: LocalDateTime,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val updatedAt: LocalDateTime
) {
  constructor(property: Property) : this(
    alternativeId = property.alternative.id,
    criteriaId = property.criteria.id,
    value = property.value,
    createdAt = property.createdAt,
    updatedAt = property.updatedAt
  )
}
