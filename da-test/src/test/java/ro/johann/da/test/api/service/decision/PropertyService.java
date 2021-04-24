package ro.johann.da.test.api.service.decision;

import com.google.inject.Inject;
import ro.johann.da.test.api.common.Storage;
import ro.johann.da.test.api.service.BaseService;
import ro.johann.da.test.api.service.HttpService;
import ro.johann.da.test.api.service.decision.transfer.PropertyOutput;
import ro.johann.da.test.api.service.decision.transfer.SetPropertyInput;
import ro.johann.da.test.api.service.Mapper;

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
