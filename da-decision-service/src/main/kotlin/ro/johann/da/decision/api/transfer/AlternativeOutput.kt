package ro.johann.da.decision.api.transfer

import com.fasterxml.jackson.annotation.JsonFormat
import ro.johann.da.decision.domain.Alternative
import java.time.LocalDateTime
import java.util.UUID

data class AlternativeOutput(
  val id: UUID,
  val name: String,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val createdAt: LocalDateTime,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val updatedAt: LocalDateTime
) {
  constructor(alternative: Alternative) : this(
    id = alternative.id,
    name = alternative.name,
    createdAt = alternative.createdAt,
    updatedAt = alternative.updatedAt
  )
}