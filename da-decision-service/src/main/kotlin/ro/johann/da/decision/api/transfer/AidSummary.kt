package ro.johann.da.decision.api.transfer

import ro.johann.da.decision.domain.ProcessingResult

class AidSummary private constructor(
  val status: String,
  val reason: String?
) {
  constructor(processingResult: ProcessingResult) : this(processingResult.status.value, processingResult.reason)
}