package ro.johann.da.decision.persistence

import org.springframework.data.repository.CrudRepository
import ro.johann.da.decision.domain.Decision
import java.util.UUID

interface DecisionRepository: CrudRepository<Decision, UUID>