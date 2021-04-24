package ro.johann.da.decision.persistence

import org.springframework.data.repository.CrudRepository
import ro.johann.da.decision.domain.Alternative
import java.util.UUID

interface AlternativeRepository : CrudRepository<Alternative, UUID> {
  fun findByIdAndDecisionId(alternativeId: UUID, decisionId: UUID): Alternative?
}
