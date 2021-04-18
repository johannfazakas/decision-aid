package ro.johann.dm.decision.api.transfer

import ro.johann.dm.decision.domain.Alternative
import java.util.UUID

data class AlternativeOutput(
  val id: UUID,
  val name: String
) {
  constructor(alternative: Alternative) : this(
    id = alternative.id,
    name = alternative.name
  )
}