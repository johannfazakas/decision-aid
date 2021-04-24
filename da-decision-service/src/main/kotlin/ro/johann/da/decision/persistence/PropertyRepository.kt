package ro.johann.da.decision.persistence

import org.springframework.data.repository.CrudRepository
import ro.johann.da.decision.domain.Property
import java.util.UUID

interface PropertyRepository : CrudRepository<Property, UUID>