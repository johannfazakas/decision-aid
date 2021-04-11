package ro.johann.dm.decision.transfer

import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class UpdateCriteriaInput(

  @field:Min(value = 0)
  @field:Max(value = 100)
  val weight: Int?
)