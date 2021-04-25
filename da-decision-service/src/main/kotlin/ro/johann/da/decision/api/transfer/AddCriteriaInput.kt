package ro.johann.da.decision.api.transfer

import ro.johann.da.decision.domain.CriteriaType
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class AddCriteriaInput(

  @field:NotBlank
  val name: String,

  @field:Min(value = 1)
  @field:Max(value = 100)
  val weight: Int,

  val unitOfMeasure: String?,

  val type: CriteriaType,
) {
}