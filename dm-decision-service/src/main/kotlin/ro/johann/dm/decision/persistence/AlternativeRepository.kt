package ro.johann.dm.decision.persistence

import org.springframework.data.repository.CrudRepository
import ro.johann.dm.decision.domain.Alternative
import java.util.UUID

interface AlternativeRepository : CrudRepository<Alternative, UUID>
