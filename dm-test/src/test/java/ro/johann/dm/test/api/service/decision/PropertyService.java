package ro.johann.dm.test.api.service.decision;

import com.google.inject.Inject;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.PropertyOutput;
import ro.johann.dm.test.api.service.decision.transfer.SetPropertyInput;

import java.util.Optional;

public class PropertyService extends BaseService {

  private static final String DECISION_MAKER_HOST = "http://localhost:7049";
  private static final String PROPERTY_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/properties";

  @Inject
  public PropertyService(HttpService httpService, Storage storage, Mapper mapper) {
    super(httpService, storage, mapper);
  }

  public Optional<PropertyOutput> addProperty(String decisionId, SetPropertyInput input) {
    return put(getPropertyUri(decisionId), mapper.serialize(input))
      .map(output -> mapper.deserialize(output, PropertyOutput.class));
  }

  private String getPropertyUri(String decisionId) {
    return PROPERTY_URI
      .replace("{decisionId}", decisionId);
  }
}
