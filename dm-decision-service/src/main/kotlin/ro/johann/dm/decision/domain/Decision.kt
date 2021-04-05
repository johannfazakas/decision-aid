package ro.johann.dm.decision.domain

import java.util.UUID

class Decision(
  val id: UUID,
  val name: String,
  val description: String?,
  val criteria: List<Criteria>,
  val candidates: List<Candidate>
)