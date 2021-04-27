package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.domain.Alternative
import ro.johann.da.decision.domain.Criteria
import ro.johann.da.decision.domain.CriteriaType
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.domain.ProcessedEntity
import ro.johann.da.decision.domain.ProcessedEntityContainer
import ro.johann.da.decision.domain.ProcessingResult
import ro.johann.da.decision.domain.Property

@Service
class ProcessDecisionCommand {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(ProcessDecisionCommand::class.java)

    const val NO_CRITERIA_DEFINED = "No criteria defined."
    const val NO_ALTERNATIVE_DEFINED = "No alternatives defined."
    const val PROPERTIES_MISSING = "Missing properties."
    const val INVALID_CRITERIA_WEIGHTS = "Total criteria weight is not 100."
  }

  fun execute(decision: Decision): ProcessingResult {
    logger.info("aid >> decision = ${decision.id}")

    return try {
      decision
        .also { validate(it) }
        .let { process(decision) }
    } catch (exception: AidException) {
      ProcessingResult.skipped(decision.id, exception.message)
    }
  }

  private fun process(decision: Decision): ProcessingResult {

    val processedProperties = decision.criteria
      .asSequence()
      .map(::processCriteriaProperties)
      .flatten()
      .toList()
      .let(ProcessedEntityContainer::fromList)

    val processedAlternatives = processAlternatives(decision.alternatives, processedProperties)
      .let(ProcessedEntityContainer::fromList)

    return ProcessingResult.processed(decision.id, processedAlternatives, processedProperties)
  }

  private fun processCriteriaProperties(criteria: Criteria): List<ProcessedEntity> {

    val minimum = criteria.properties.minOf(Property::value)
    val maximum = criteria.properties.maxOf(Property::value)

    return when (criteria.type) {
      CriteriaType.MINIMUM -> {
        processProperties(
          properties = criteria.properties,
          rankSolver = { value -> 1 + criteria.properties.count { property -> property.value < value } },
          utilitySolver = { value -> criteria.weight * 0.01 * (maximum - value) / (maximum - minimum) }
        )
      }
      CriteriaType.MAXIMUM -> {
        processProperties(
          properties = criteria.properties,
          rankSolver = { value -> 1 + criteria.properties.count { property -> property.value > value } },
          utilitySolver = { value -> criteria.weight * 0.01 * (value - minimum) / (maximum - minimum) }
        )
      }
    }
  }

  private fun processProperties(
    properties: List<Property>,
    rankSolver: (Double) -> Int,
    utilitySolver: (Double) -> Double
  ): List<ProcessedEntity> =
    properties.map { property ->
      ProcessedEntity(
        id = property.id,
        rank = rankSolver(property.value),
        utility = utilitySolver(property.value)
      )
    }

  private fun processAlternatives(
    alternatives: List<Alternative>,
    processedProperties: ProcessedEntityContainer
  ): List<ProcessedEntity> {
    val alternativeUtilities = alternatives
      .asSequence()
      .map { alternative ->
        alternative.id to alternative.properties
          .asSequence()
          .map(Property::id)
          .mapNotNull(processedProperties::get)
          .map(ProcessedEntity::utility)
          .sum()
      }
      .toMap()

    return alternatives.map { alternative ->
      val utility = alternativeUtilities[alternative.id]!!
      ProcessedEntity(
        id = alternative.id,
        rank = 1 + alternativeUtilities.values.count { it > utility },
        utility = utility
      )
    }
  }

  private fun validate(decision: Decision): Unit = when {
    decision.criteria.isEmpty() -> throw AidException(NO_CRITERIA_DEFINED)
    decision.alternatives.isEmpty() -> throw AidException(NO_ALTERNATIVE_DEFINED)
    !decision.allPropertiesAreSet() -> throw AidException(PROPERTIES_MISSING)
    !decision.hasValidCriteriaWeights() -> throw AidException(INVALID_CRITERIA_WEIGHTS)
    else -> {
    }
  }

  class AidException(override val message: String) : RuntimeException(message)
}