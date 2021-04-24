package ro.johann.da.decision.persistence

import org.springframework.data.repository.CrudRepository
import ro.johann.da.decision.domain.Criteria
import java.util.UUID

interface CriteriaRepository: CrudRepository<Criteria, UUID> {
  fun findByIdAndDecisionId(criteriaId: UUID, decisionId: UUID): Criteria?
}