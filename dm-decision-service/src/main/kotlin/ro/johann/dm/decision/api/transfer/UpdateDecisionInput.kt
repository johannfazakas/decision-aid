package ro.johann.dm.decision.api.transfer

import ro.johann.dm.decision.api.validation.NotEmptyIfPresent

data class UpdateDecisionInput(

  @field:NotEmptyIfPresent
  val name: String?,
  val description: String?
)