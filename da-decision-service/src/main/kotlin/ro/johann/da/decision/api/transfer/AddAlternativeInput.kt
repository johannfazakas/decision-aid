package ro.johann.da.decision.api.transfer

import javax.validation.constraints.NotBlank

data class AddAlternativeInput(
  @field:NotBlank
  val name: String
)