package ro.johann.dm.decision.api.transfer

import ro.johann.dm.decision.domain.Alternative
import java.util.UUID

data class AlternativeOutput(
  val id: UUID,
  val name: String,
  val properties: List<PropertyOutput>
) {
  constructor(alternative: Alternative) : this(
    id = alternative.id,
    name = alternative.name,
    properties = alternative.properties.map(::PropertyOutput)
  )
}