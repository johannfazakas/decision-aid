package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.UpdateAlternativeInput
import ro.johann.da.decision.domain.Alternative
import ro.johann.da.decision.persistence.AlternativeRepository
import ro.johann.da.decision.service.error.Errors
import java.time.LocalDateTime
import java.util.UUID

@Service
class UpdateAlternativeCommand(
  private val alternativeRepository: AlternativeRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(UpdateAlternativeCommand::class.java)
  }

  fun execute(decisionId: UUID, alternativeId: UUID, input: UpdateAlternativeInput): Alternative {
    logger.info("update alternative >> decisionId = $decisionId, alternativeId = $alternativeId, input = $input")

    val alternative = alternativeRepository.findByIdAndDecisionId(alternativeId, decisionId)
      ?: throw Errors.alternativeNotFound(decisionId, alternativeId)

    return alternative
      .apply {
        input.name?.let { name = it }
        updatedAt = LocalDateTime.now()
      }
      .also(alternativeRepository::save)
  }
}
