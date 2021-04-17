package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.dm.decision.api.transfer.AddAlternativeInput
import ro.johann.dm.decision.domain.Alternative
import ro.johann.dm.decision.persistence.AlternativeRepository
import ro.johann.dm.decision.persistence.DecisionRepository
import ro.johann.dm.decision.service.error.Errors
import java.util.UUID

@Service
class AddAlternativeCommand(
  private val decisionRepository: DecisionRepository,
  private val alternativeRepository: AlternativeRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(AddAlternativeCommand::class.java)
  }

  fun execute(decisionId: UUID, input: AddAlternativeInput): Alternative {
    logger.info("add alternative >> decisionId = $decisionId, input = $input")

    return decisionRepository.findByIdOrNull(decisionId)
      ?.let { decision -> alternativeRepository.save(input.toModel(decision)) }
      ?: throw Errors.decisionNotFound(decisionId)
  }
}
