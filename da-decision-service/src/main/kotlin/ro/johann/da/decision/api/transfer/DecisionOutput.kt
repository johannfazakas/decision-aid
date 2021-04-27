package ro.johann.da.decision.api.transfer

import com.fasterxml.jackson.annotation.JsonFormat
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.domain.ProcessingResult
import java.time.LocalDateTime
import java.util.UUID

data class DecisionOutput(
  val id: UUID?,
  val name: String?,
  val description: String?,
  val status: String,
  val aidSummary: AidSummary? = null,
  val criteria: List<CriteriaOutput> = emptyList(),
  val alternatives: List<AlternativeOutput> = emptyList(),
  val properties: List<PropertyOutput> = emptyList(),
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val createdAt: LocalDateTime,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  val updatedAt: LocalDateTime
) {
  constructor(
    decision: Decision,
    processingResult: ProcessingResult? = null
  ) : this(
    id = decision.id,
    name = decision.name,
    description = decision.description,
    status = decision.status.value,
    aidSummary = processingResult?.let(::AidSummary),
    criteria = decision.criteria.map(::CriteriaOutput),
    alternatives = decision.alternatives
      .map { alternative -> AlternativeOutput(alternative, processingResult?.getProcessedAlternative(alternative.id)) },
    properties = decision.alternatives
      .flatMap { it.properties }
      .map { property -> PropertyOutput(property, processingResult?.getProcessedProperty(property.id)) },
    createdAt = decision.createdAt,
    updatedAt = decision.updatedAt
  )
}
