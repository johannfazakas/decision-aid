package ro.johann.dm.decision.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ro.johann.dm.decision.domain.Criteria
import ro.johann.dm.decision.persistence.CriteriaRepository
import ro.johann.dm.decision.persistence.DecisionRepository
import ro.johann.dm.decision.transfer.AddCriteriaRequest
import java.lang.RuntimeException
import java.util.UUID

@Service
class AddCriteriaCommand(
  private val decisionRepository: DecisionRepository,
  private val criteriaRepository: CriteriaRepository
) {
  private companion object {
    val logger: Logger = LoggerFactory.getLogger(AddCriteriaCommand::class.java)
  }

  fun execute(decisionId: UUID, request: AddCriteriaRequest): Criteria {
    logger.info("add criteria >> decisionId = $decisionId, request = $request")

    val decision = decisionRepository.findByIdOrNull(decisionId)
      ?: throw RuntimeException("Decision not found")
    return criteriaRepository.save(request.toModel(decision))
  }
}