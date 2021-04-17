package ro.johann.dm.test.api.service.decision;

import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.AddAlternativeInput;
import ro.johann.dm.test.api.service.decision.transfer.AlternativeOutput;

import javax.inject.Inject;
import java.util.Optional;

public class AlternativeService extends BaseService {

  // TODO extract configs
  private static final String DECISION_MAKER_HOST = "http://localhost:7049";
  private static final String ADD_ALTERNATIVE_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/alternatives";

  @Inject
  public AlternativeService(HttpService httpService, Storage storage, Mapper mapper) {
    super(httpService, storage, mapper);
  }

  public Optional<AlternativeOutput> addAlternative(String decisionId, AddAlternativeInput input) {
    String uri = ADD_ALTERNATIVE_URI
      .replace("{decisionId}", decisionId);
    return post(uri, mapper.serialize(input))
      .map(response -> mapper.deserialize(response, AlternativeOutput.class));
  }
}
