package ro.johann.dm.decision.domain

import java.util.UUID

class Alternative(
  val id: UUID,
  val name: String,
  val link: String?,
  val properties: List<Property>
)
