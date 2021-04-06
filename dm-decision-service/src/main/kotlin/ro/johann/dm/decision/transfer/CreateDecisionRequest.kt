package ro.johann.dm.decision.transfer

data class CreateDecisionRequest(
  val name: String,
  val description: String?
)