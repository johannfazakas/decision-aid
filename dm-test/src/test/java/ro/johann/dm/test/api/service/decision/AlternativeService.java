package ro.johann.dm.test.api.service.decision;

import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.AddAlternativeInput;
import ro.johann.dm.test.api.service.decision.transfer.AlternativeOutput;
import ro.johann.dm.test.api.service.decision.transfer.UpdateAlternativeInput;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class AlternativeService extends BaseService {

  @Inject
  public AlternativeService(HttpService httpService, Storage storage, Mapper mapper, Properties properties) {
    super(httpService, storage, mapper, properties);
  }

  public Optional<AlternativeOutput> addAlternative(String decisionId, AddAlternativeInput input) {
    var url = getUrl("decisionApi.alternativesUrl", Map.of("decisionId", decisionId));
    return post(url, mapper.serialize(input)).map(output -> mapper.deserialize(output, AlternativeOutput.class));
  }

  public void deleteAlternative(String decisionId, String alternativeId) {
    var url = getUrl("decisionApi.alternativeByIdUrl",
      Map.of("decisionId", decisionId, "alternativeId", alternativeId));
    delete(url);
  }

  public Optional<AlternativeOutput> updateAlternative(String decisionId, String alternativeId, UpdateAlternativeInput input) {
    var url = getUrl("decisionApi.alternativeByIdUrl",
      Map.of("decisionId", decisionId, "alternativeId", alternativeId));
    return patch(url, mapper.serialize(input)).map(output -> mapper.deserialize(output, AlternativeOutput.class));
  }
}
