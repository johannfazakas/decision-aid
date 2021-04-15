package ro.johann.dm.decision.service.error

import java.lang.RuntimeException
import java.util.UUID

object Errors {
  fun decisionNotFound(decisionId: UUID) =
    NotFoundException("Decision $decisionId not found.")

  fun criteriaNotFound(decisionId: UUID, criteriaId: UUID) =
    NotFoundException("Criteria $criteriaId on decision $decisionId not found.")
}

class NotFoundException(override val message: String) : RuntimeException(message)