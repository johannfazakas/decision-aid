package ro.johann.dm.decision.persistence

import org.springframework.stereotype.Repository
import ro.johann.dm.decision.domain.Decision
import java.util.UUID

@Repository
class DecisionRepositoryImpl: DecisionRepository {
  override fun findById(id: UUID): Decision = Decision(
    id = id,
    name = "mock-name",
    description = "mock-description"
  )

  override fun findAll(): List<Decision> = listOf(
    Decision(
      id = UUID.randomUUID(),
      name = "mock-name-1",
      description = "mock-description-1"
    ),
    Decision(
      id = UUID.randomUUID(),
      name = "mock-name-2",
      description = null
    )
  )

  override fun delete(id: UUID) { }

  override fun save(decision: Decision): Decision = Decision(
    id = UUID.randomUUID(),
    name = decision.name,
    description = decision.description
  )
}