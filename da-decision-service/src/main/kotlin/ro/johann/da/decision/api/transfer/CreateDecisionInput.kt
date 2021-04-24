package ro.johann.da.decision.api.transfer

import javax.validation.constraints.NotBlank

data class CreateDecisionInput(

  @field:NotBlank
  val name: String,

  val description: String?
)