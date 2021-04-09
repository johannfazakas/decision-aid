package ro.johann.dm.decision.transfer

import ro.johann.dm.decision.domain.Criteria
import ro.johann.dm.decision.domain.Decision

data class AddCriteriaRequest(
  val name: String,
  val weight: Int
) {
  fun toModel(decision: Decision): Criteria = Criteria(
    name = name,
    weight = weight,
    decision = decision
  )
}