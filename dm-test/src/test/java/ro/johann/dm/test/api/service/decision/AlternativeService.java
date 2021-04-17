package ro.johann.dm.test.api.service.decision;

import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.AddAlternativeInput;
import ro.johann.dm.test.api.service.decision.transfer.AlternativeOutput;
import ro.johann.dm.test.api.service.decision.transfer.UpdateAlternativeInput;

import javax.inject.Inject;
import java.util.Optional;

public class AlternativeService extends BaseService {

  // TODO extract configs
  private static final String DECISION_MAKER_HOST = "http://localhost:7049";
  private static final String ALTERNATIVE_BY_ID_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/alternatives/{alternativeId}";
  private static final String ALTERNATIVES_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/alternatives";

  @Inject
  public AlternativeService(HttpService httpService, Storage storage, Mapper mapper) {
    super(httpService, storage, mapper);
  }

  public Optional<AlternativeOutput> addAlternative(String decisionId, AddAlternativeInput input) {
    return post(getAlternativesUri(decisionId), mapper.serialize(input))
      .map(output -> mapper.deserialize(output, AlternativeOutput.class));
  }

  public void deleteAlternative(String decisionId, String alternativeId) {
    delete(getAlternativeByIdUri(decisionId, alternativeId));
  }

  public Optional<AlternativeOutput> updateAlternative(String decisionId, String alternativeId, UpdateAlternativeInput input) {
    return patch(getAlternativeByIdUri(decisionId, alternativeId), mapper.serialize(input))
      .map(output -> mapper.deserialize(output, AlternativeOutput.class));
  }

  private String getAlternativesUri(String decisionId) {
    return ALTERNATIVES_URI
      .replace("{decisionId}", decisionId);
  }

  private String getAlternativeByIdUri(String decisionId, String alternativeId) {
    return ALTERNATIVE_BY_ID_URI
      .replace("{decisionId}", decisionId)
      .replace("{alternativeId}", alternativeId);
  }
}
