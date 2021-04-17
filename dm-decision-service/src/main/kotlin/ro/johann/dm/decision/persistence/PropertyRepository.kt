package ro.johann.dm.decision.persistence

import org.springframework.data.repository.CrudRepository
import ro.johann.dm.decision.domain.Property
import java.util.UUID

interface PropertyRepository : CrudRepository<Property, UUID>