package ro.johann.dm.decision.persistence

import ro.johann.dm.decision.domain.Decision
import java.util.UUID

interface DecisionRepository {
  fun findById(id: UUID): Decision
  fun findAll(): List<Decision>
  fun delete(id: UUID)
  fun save(decision: Decision): Decision
}