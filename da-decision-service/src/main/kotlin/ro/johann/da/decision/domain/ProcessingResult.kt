package ro.johann.da.decision.domain

import java.util.UUID

class ProcessingResult private constructor(
  val decisionId: UUID,
  val status: ProcessingStatus,
  val reason: String? = null,
  private val alternatives: ProcessedEntityContainer = ProcessedEntityContainer.empty(),
  private val properties: ProcessedEntityContainer = ProcessedEntityContainer.empty()
) {
  companion object {
    fun processed(
      decisionId: UUID,
      alternatives: ProcessedEntityContainer,
      properties: ProcessedEntityContainer
    ) = ProcessingResult(
      decisionId = decisionId,
      status = ProcessingStatus.PROCESSED,
      alternatives = alternatives,
      properties = properties
    )

    fun skipped(decisionId: UUID, reason: String) = ProcessingResult(
      decisionId = decisionId,
      status = ProcessingStatus.SKIPPED,
      reason = reason
    )
  }

  fun getProcessedAlternative(alternativeId: UUID) = alternatives[alternativeId]
  fun getProcessedProperty(propertyId: UUID) = properties[propertyId]
}

enum class ProcessingStatus(val value: String) {
  PROCESSED("processed"),
  SKIPPED("skipped")
}

data class ProcessedEntity(
  val id: UUID,
  val rank: Int,
  val utility: Double
)

class ProcessedEntityContainer private constructor(processedEntities: List<ProcessedEntity>) {
  companion object {
    fun fromList(processedEntities: List<ProcessedEntity>) = ProcessedEntityContainer(processedEntities)
    fun empty() = ProcessedEntityContainer(emptyList())
  }

  private val processedEntitiesById = processedEntities
    .asSequence()
    .map { processedEntity -> processedEntity.id to processedEntity }
    .toMap()

  operator fun get(id: UUID) = processedEntitiesById[id]
}