package ro.johann.dm.test.api.service.decision;

import com.google.inject.Inject;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.PropertyOutput;
import ro.johann.dm.test.api.service.decision.transfer.SetPropertyInput;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class PropertyService extends BaseService {

  @Inject
  public PropertyService(HttpService httpService, Storage storage, Mapper mapper, Properties properties) {
    super(httpService, storage, mapper, properties);
  }

  public Optional<PropertyOutput> addProperty(String decisionId, SetPropertyInput input) {
    var url = getUrl("decisionApi.propertiesUrl", Map.of("decisionId", decisionId));
    return put(url, mapper.serialize(input)).map(output -> mapper.deserialize(output, PropertyOutput.class));
  }
}
