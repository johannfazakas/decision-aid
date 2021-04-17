package ro.johann.dm.decision.api.transfer

import ro.johann.dm.decision.api.validation.NotBlankIfPresent

data class UpdateAlternativeInput(
  @field:NotBlankIfPresent
  val name: String?
)
