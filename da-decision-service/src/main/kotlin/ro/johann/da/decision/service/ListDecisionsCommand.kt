package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.domain.Decision
import ro.johann.da.decision.persistence.DecisionRepository

@Service
class ListDecisionsCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(ListDecisionsCommand::class.java)
  }

  fun execute(): Iterable<Decision> {
    logger.info("list decisions")

    return decisionRepository.findAll().sortedBy { it.createdAt }
  }
}