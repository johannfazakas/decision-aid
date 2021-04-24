package ro.johann.da.decision.api.transfer

import ro.johann.da.decision.api.validation.NotBlankIfPresent

data class UpdateAlternativeInput(
  @field:NotBlankIfPresent
  val name: String?
)
