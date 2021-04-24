package ro.johann.da.decision.api.transfer

import ro.johann.da.decision.domain.Alternative
import ro.johann.da.decision.domain.Decision
import javax.validation.constraints.NotBlank

data class AddAlternativeInput(
  @field:NotBlank
  val name: String
) {
  fun toModel(decision: Decision): Alternative = Alternative(
    name = name,
    decision = decision
  )
}