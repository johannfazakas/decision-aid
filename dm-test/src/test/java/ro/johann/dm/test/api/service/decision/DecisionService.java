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
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static java.util.Collections.emptyMap;

public class DecisionService extends BaseService {

  @Inject
  public DecisionService(HttpService httpService, Mapper mapper, Storage storage, Properties properties) {
    super(httpService, storage, mapper, properties);
  }

  public Optional<DecisionOutput> createDecision(CreateDecisionInput input) {
    var url = getUrl("decisionApi.decisionsUrl", emptyMap());
    return post(url, mapper.serialize(input)).map(output -> mapper.deserialize(output, DecisionOutput.class));
  }

  public Optional<DecisionOutput> getDecision(String decisionId) {
    var url = getUrl("decisionApi.decisionByIdUrl", Map.of("decisionId", decisionId));
    return get(url).map(output -> mapper.deserialize(output, DecisionOutput.class));
  }

  public Optional<DecisionsOutput> listDecisions() {
    var url = getUrl("decisionApi.decisionsUrl", emptyMap());
    return get(url).map(output -> mapper.deserialize(output, DecisionsOutput.class));
  }

  public Optional<DecisionOutput> updateDecision(String decisionId, UpdateDecisionInput input) {
    var url = getUrl("decisionApi.decisionByIdUrl", Map.of("decisionId", decisionId));
    return patch(url, mapper.serialize(input)).map(output -> mapper.deserialize(output, DecisionOutput.class));
  }

  public void deleteDecision(String decisionId) {
    var url = getUrl("decisionApi.decisionByIdUrl", Map.of("decisionId", decisionId));
    delete(url);
  }

  public Optional<DecisionOutput> aidDecision(String decisionId) {
    var url = getUrl("decisionApi.aidDecisionUrl", Map.of("decisionId", decisionId));
    return put(url, new byte[0]).map(output -> mapper.deserialize(output, DecisionOutput.class));
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
