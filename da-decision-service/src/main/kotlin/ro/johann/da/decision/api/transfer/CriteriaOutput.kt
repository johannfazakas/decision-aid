package ro.johann.da.decision.api.transfer

import com.fasterxml.jackson.annotation.JsonFormat
import ro.johann.da.decision.domain.Criteria
import java.time.LocalDateTime
import java.util.UUID

data class CriteriaOutput(
  val id: UUID,
  val name: String,
  val weight: Int,
  val unitOfMeasure: String?,
  val type: String,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val createdAt: LocalDateTime,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val updatedAt: LocalDateTime
) {
  constructor(criteria: Criteria) : this(
    id = criteria.id,
    name = criteria.name,
    weight = criteria.weight,
    unitOfMeasure = criteria.unitOfMeasure,
    type = criteria.type.value,
    createdAt = criteria.createdAt,
    updatedAt = criteria.updatedAt
  )
}