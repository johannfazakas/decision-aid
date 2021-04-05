package ro.johann.dm.decision.transfer

data class ListResponse<T>(
  val items: List<T>,
  val total: Int
)
