package ro.johann.dm.decision.api.transfer

import ro.johann.dm.decision.api.validation.NotBlankIfPresent
import ro.johann.dm.decision.domain.CriteriaType
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class UpdateCriteriaInput(

  @field:Min(value = 1)
  @field:Max(value = 100)
  val weight: Int?,

  @field:NotBlankIfPresent
  val name: String?,

  val unitOfMeasure: String?,

  val type: CriteriaType?
)