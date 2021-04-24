package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.da.decision.api.transfer.AddCriteriaInput
import ro.johann.da.decision.domain.Criteria
import ro.johann.da.decision.persistence.CriteriaRepository
import ro.johann.da.decision.persistence.DecisionRepository
import ro.johann.da.decision.service.error.Errors
import java.util.UUID

@Service
class AddCriteriaCommand(
  private val decisionRepository: DecisionRepository,
  private val criteriaRepository: CriteriaRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(AddCriteriaCommand::class.java)
  }

  fun execute(decisionId: UUID, input: AddCriteriaInput): Criteria {
    logger.info("add criteria >> decisionId = $decisionId, input = $input")

    return decisionRepository.findByIdOrNull(decisionId)
      ?.let { decision -> criteriaRepository.save(input.toModel(decision)) }
      ?: throw Errors.decisionNotFound(decisionId)
  }
}