package ro.johann.da.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.da.decision.domain.Criteria
import ro.johann.da.decision.domain.DecisionStatus
import ro.johann.da.decision.persistence.CriteriaRepository
import ro.johann.da.decision.service.error.Errors
import java.util.UUID

@Service
class DeleteCriteriaCommand(
  private val criteriaRepository: CriteriaRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(DeleteCriteriaCommand::class.java)
  }

  fun execute(decisionId: UUID, criteriaId: UUID) {
    logger.info("delete criteria >> decisionId = $decisionId, criteriaId = $criteriaId")

    criteriaRepository.findByIdAndDecisionId(criteriaId, decisionId)
      ?.also { validate(it) }
      ?.also { criteriaRepository.deleteById(criteriaId) }
  }

  private fun validate(criteria: Criteria) {
    if (criteria.decision.status === DecisionStatus.PROCESSED) {
      throw Errors.invalidDecisionStatus(criteria.decision.id, DecisionStatus.PROCESSED)
    }
  }
}