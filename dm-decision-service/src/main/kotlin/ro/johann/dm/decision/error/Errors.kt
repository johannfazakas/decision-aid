package ro.johann.dm.decision.error

import java.lang.RuntimeException
import java.util.UUID

object Errors {
  fun decisionNotFound(id: UUID) = NotFoundException("Decision $id not found.")
}

class NotFoundException(override val message: String) : RuntimeException(message)