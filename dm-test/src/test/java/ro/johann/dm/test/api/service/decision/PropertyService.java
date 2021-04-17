package ro.johann.dm.test.api.service.decision;

import com.google.inject.Inject;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.SetPropertyInput;

public class PropertyService extends BaseService {

  private static final String DECISION_MAKER_HOST = "http://localhost:7049";
  private static final String PROPERTY_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/alternatives/{alternativeId}/criteria/{criteriaId}/property";

  @Inject
  public PropertyService(HttpService httpService, Storage storage, Mapper mapper) {
    super(httpService, storage, mapper);
  }

  public void addProperty(String decisionId, String alternativeId, String criteriaId, SetPropertyInput input) {
    put(getPropertyUri(decisionId, alternativeId, criteriaId), mapper.serialize(input));
  }

  private String getPropertyUri(String decisionId, String alternativeId, String criteriaId) {
    return PROPERTY_URI
      .replace("{decisionId}", decisionId)
      .replace("{alternativeId}", alternativeId)
      .replace("{criteriaId}", criteriaId);
  }
}
