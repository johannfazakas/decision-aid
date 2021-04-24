package ro.johann.da.test.api.service.decision;

import ro.johann.da.test.api.common.Storage;
import ro.johann.da.test.api.service.HttpService;
import ro.johann.da.test.api.service.decision.transfer.CriteriaInput;
import ro.johann.da.test.api.service.BaseService;
import ro.johann.da.test.api.service.Mapper;
import ro.johann.da.test.api.service.decision.transfer.CriteriaOutput;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class CriteriaService extends BaseService {

  @Inject
  public CriteriaService(HttpService httpService, Storage storage, Mapper mapper, Properties properties) {
    super(httpService, storage, mapper, properties);
  }

  public Optional<CriteriaOutput> addCriteria(String decisionId, CriteriaInput input) {
    var url = getUrl("decisionApi.criteriaUrl", Map.of("decisionId", decisionId));
    return post(url, mapper.serialize(input)).map(response -> mapper.deserialize(response, CriteriaOutput.class));
  }

  public Optional<CriteriaOutput> updateCriteria(String decisionId, String criteriaId, CriteriaInput input) {
    var url = getUrl("decisionApi.criteriaByIdUrl",
      Map.of("decisionId", decisionId, "criteriaId", criteriaId));
    return patch(url, mapper.serialize(input)).map(response -> mapper.deserialize(response, CriteriaOutput.class));
  }

  public void deleteCriteria(String decisionId, String criteriaId) {
    var url = getUrl("decisionApi.criteriaByIdUrl",
      Map.of("decisionId", decisionId, "criteriaId", criteriaId));
    delete(url);
  }
}
