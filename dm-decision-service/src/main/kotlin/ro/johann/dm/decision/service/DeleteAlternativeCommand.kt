package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.dm.decision.persistence.AlternativeRepository
import java.util.UUID

@Service
class DeleteAlternativeCommand(
  private val alternativeRepository: AlternativeRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(DeleteAlternativeCommand::class.java)
  }

  fun execute(decisionId: UUID, alternativeId: UUID) {
    logger.info("delete alternative >> decisionId = $decisionId, alternativeId = $alternativeId")

    alternativeRepository.findByIdAndDecisionId(alternativeId, decisionId)
      .also { alternativeRepository.deleteById(alternativeId) }
  }
}
