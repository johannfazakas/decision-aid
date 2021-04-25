package ro.johann.da.decision.service.error

import java.util.UUID

object Errors {
  fun decisionNotFound(decisionId: UUID) =
    NotFoundException("Decision $decisionId not found.")

  fun criteriaNotFound(decisionId: UUID, criteriaId: UUID) =
    NotFoundException("Criteria $criteriaId on decision $decisionId not found.")

  fun alternativeNotFound(decisionId: UUID, alternativeId: UUID) =
    NotFoundException("Alternative $alternativeId on decision $decisionId not found.")

  fun noCriteriaDefined(decisionId: UUID) =
    ConflictException("Decision $decisionId has no criteria defined.")

  fun noAlternativeDefined(decisionId: UUID) =
    ConflictException("Decision $decisionId has no alternative defined.")

  fun propertiesAreMissing(decisionId: UUID) =
    ConflictException("Decision $decisionId has missing properties.")

  fun invalidCriteriaWeights(decisionId: UUID) =
    ConflictException("Decision $decisionId has invalid criteria weights.")
}

class NotFoundException(override val message: String) : RuntimeException(message)

class ConflictException(override val message: String) : RuntimeException(message)
