package ro.johann.dm.decision.persistence

import org.springframework.data.repository.CrudRepository
import ro.johann.dm.decision.domain.Decision
import java.util.UUID

interface DecisionRepository: CrudRepository<Decision, UUID>