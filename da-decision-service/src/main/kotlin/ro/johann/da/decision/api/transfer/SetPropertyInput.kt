package ro.johann.da.decision.api.transfer

import java.util.UUID
import javax.validation.constraints.NotNull

data class SetPropertyInput(

  @field:NotNull
  val alternativeId: UUID,

  @field:NotNull
  val criteriaId: UUID,

  @field:NotNull
  val value: Double
)
