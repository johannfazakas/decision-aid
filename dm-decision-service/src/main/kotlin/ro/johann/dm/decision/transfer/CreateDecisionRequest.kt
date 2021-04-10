package ro.johann.dm.decision.transfer

import javax.validation.constraints.NotEmpty

data class CreateDecisionRequest(

  @field:NotEmpty(message = "Decision name is mandatory.")
  val name: String,

  val description: String?
)