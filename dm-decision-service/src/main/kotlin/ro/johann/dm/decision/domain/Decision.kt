package ro.johann.dm.decision.domain

import java.util.UUID

class Decision(
  val id: UUID?,
  val name: String,
  val description: String?,
  val criteria: List<Criteria> = emptyList(),
  val alternatives: List<Alternative> = emptyList()
) {
  constructor(
    name: String,
    description: String?
  ): this(
    id = null,
    name = name,
    description = description
  )
}