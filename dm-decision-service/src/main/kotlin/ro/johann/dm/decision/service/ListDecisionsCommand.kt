package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.dm.decision.domain.Decision
import ro.johann.dm.decision.persistence.DecisionRepository

@Service
class ListDecisionsCommand(
  private val decisionRepository: DecisionRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(ListDecisionsCommand::class.java)
  }

  fun execute(): List<Decision> {
    logger.info("list decisions")
    return decisionRepository.findAll()
  }
}