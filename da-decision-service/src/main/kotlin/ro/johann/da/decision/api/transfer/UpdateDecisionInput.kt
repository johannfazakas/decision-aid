package ro.johann.da.decision.api.transfer

import ro.johann.da.decision.api.validation.NotBlankIfPresent

data class UpdateDecisionInput(

  @field:NotBlankIfPresent
  val name: String?,
  val description: String?
)