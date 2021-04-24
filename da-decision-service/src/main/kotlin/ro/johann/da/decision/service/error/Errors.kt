package ro.johann.da.decision.service.error

import java.util.UUID

object Errors {
  fun decisionNotFound(decisionId: UUID) =
    NotFoundException("Decision $decisionId not found.")

  fun criteriaNotFound(decisionId: UUID, criteriaId: UUID) =
    NotFoundException("Criteria $criteriaId on decision $decisionId not found.")

  fun alternativeNotFound(decisionId: UUID, alternativeId: UUID) =
    NotFoundException("Alternative $alternativeId on decision $decisionId not found.")
}

class NotFoundException(override val message: String) : RuntimeException(message)