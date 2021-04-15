package ro.johann.dm.test.api.service.decision;

import lombok.SneakyThrows;
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
  private static final String CREATE_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions";
  private static final String GET_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}";
  private static final String LIST_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions";
  private static final String UPDATE_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}";
  private static final String DELETE_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}";

  @Inject
  public DecisionService(HttpService httpService, Mapper mapper, Storage storage) {
    super(httpService, storage, mapper);
  }

  @SneakyThrows
  public Optional<DecisionOutput> createDecision(CreateDecisionInput input) {
    return post(CREATE_DECISION_URI, mapper.serialize(input))
      .map(output -> mapper.deserialize(output, DecisionOutput.class));
  }

  @SneakyThrows
  public Optional<DecisionOutput> getDecision(String decisionId) {
    return get(GET_DECISION_URI.replace("{decisionId}", decisionId))
      .map(output -> mapper.deserialize(output, DecisionOutput.class));
  }

  @SneakyThrows
  public Optional<DecisionsOutput> listDecisions() {
    return get(LIST_DECISION_URI)
      .map(output -> mapper.deserialize(output, DecisionsOutput.class));
  }

  @SneakyThrows
  public Optional<DecisionOutput> updateDecision(String decisionId, UpdateDecisionInput input) {
    return patch(UPDATE_DECISION_URI.replace("{decisionId}", decisionId), mapper.serialize(input))
      .map(output -> mapper.deserialize(output, DecisionOutput.class));
  }

  @SneakyThrows
  public void deleteDecision(String decisionId) {
    delete(DELETE_DECISION_URI.replace("{decisionId}", decisionId));
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
}
