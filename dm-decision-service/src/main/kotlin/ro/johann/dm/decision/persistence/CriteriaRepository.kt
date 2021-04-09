package ro.johann.dm.decision.persistence

import org.springframework.data.repository.CrudRepository
import ro.johann.dm.decision.domain.Criteria
import java.util.UUID

interface CriteriaRepository: CrudRepository<Criteria, UUID> {
  fun findByIdAndDecisionId(criteriaId: UUID, decisionId: UUID): Criteria?
  fun deleteByIdAndDecisionId(criteriaId: UUID, decisionId: UUID)
}