package ro.johann.dm.decision.api.transfer

import ro.johann.dm.decision.domain.Alternative
import ro.johann.dm.decision.domain.Decision
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