package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ro.johann.dm.decision.domain.Criteria
import ro.johann.dm.decision.persistence.CriteriaRepository
import ro.johann.dm.decision.transfer.UpdateCriteriaRequest
import java.util.UUID

@Service
class UpdateCriteriaCommand(
  private val criteriaRepository: CriteriaRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(UpdateCriteriaCommand::class.java)
  }

  fun execute(decisionId: UUID, criteriaId: UUID, request: UpdateCriteriaRequest): Criteria {
    logger.info("update criteria >> decisionId = $decisionId, criteriaId = $criteriaId, request = $request")

    val criteria = criteriaRepository.findByIdAndDecisionId(criteriaId, decisionId)
      ?: throw RuntimeException("Criteria not found")
    request.weight?.also { criteria.weight = it }
    criteriaRepository.save(criteria)
    return criteria
  }
}