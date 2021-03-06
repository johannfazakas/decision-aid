package ro.johann.da.decision.api.transfer

import ro.johann.da.decision.api.validation.NotBlankIfPresent
import ro.johann.da.decision.domain.CriteriaType
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