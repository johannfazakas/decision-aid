package ro.johann.dm.test.api.service.decision;

import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.CreateDecisionInput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionOutput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionsOutput;
import ro.johann.dm.test.api.service.decision.transfer.UpdateDecisionInput;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DecisionService extends BaseService {

  // TODO extract to configs
  private static final String DECISION_MAKER_HOST = "http://localhost:7049";
  private static final String DECISIONS_URI = DECISION_MAKER_HOST + "/decision/v1/decisions";
  private static final String DECISION_BY_ID_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}";

  @Inject
  public DecisionService(HttpService httpService, Mapper mapper, Storage storage) {
    super(httpService, storage, mapper);
  }

  public Optional<DecisionOutput> createDecision(CreateDecisionInput input) {
    return post(getDecisionsUri(), mapper.serialize(input))
      .map(output -> mapper.deserialize(output, DecisionOutput.class));
  }

  public Optional<DecisionOutput> getDecision(String decisionId) {
    return get(getDecisionByIdUri(decisionId))
      .map(output -> mapper.deserialize(output, DecisionOutput.class));
  }

  public Optional<DecisionsOutput> listDecisions() {
    return get(getDecisionsUri())
      .map(output -> mapper.deserialize(output, DecisionsOutput.class));
  }

  public Optional<DecisionOutput> updateDecision(String decisionId, UpdateDecisionInput input) {
    return patch(getDecisionByIdUri(decisionId), mapper.serialize(input))
      .map(output -> mapper.deserialize(output, DecisionOutput.class));
  }

  public void deleteDecision(String decisionId) {
    delete(getDecisionByIdUri(decisionId).replace("{decisionId}", decisionId));
  }

  // TODO extract cleanup logic
  public void cleanUp() {
    listDecisions()
      .map(DecisionsOutput::getItems)
      .orElseGet(List::of)
      .stream()
      .map(DecisionOutput::getId)
      .forEach(this::deleteDecision);
  }

  private String getDecisionByIdUri(String decisionId) {
    return DECISION_BY_ID_URI
      .replace("{decisionId}", decisionId);
  }

  private String getDecisionsUri() {
    return DECISIONS_URI;
  }
}
