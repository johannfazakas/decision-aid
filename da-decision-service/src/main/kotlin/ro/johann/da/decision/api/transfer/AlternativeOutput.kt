package ro.johann.da.decision.api.transfer

import com.fasterxml.jackson.annotation.JsonFormat
import ro.johann.da.decision.domain.Alternative
import ro.johann.da.decision.domain.ProcessedEntity
import java.time.LocalDateTime
import java.util.UUID

data class AlternativeOutput(
  val id: UUID,
  val name: String,
  val rank: Int? = null,
  val utility: Double? = null,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val createdAt: LocalDateTime,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val updatedAt: LocalDateTime
) {
  constructor(alternative: Alternative, processedEntity: ProcessedEntity? = null) : this(
    id = alternative.id,
    name = alternative.name,
    rank = processedEntity?.rank,
    utility = processedEntity?.utility,
    createdAt = alternative.createdAt,
    updatedAt = alternative.updatedAt
  )
}