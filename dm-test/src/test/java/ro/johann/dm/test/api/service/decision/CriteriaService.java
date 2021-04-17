package ro.johann.dm.test.api.service.decision;

import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.AddCriteriaInput;
import ro.johann.dm.test.api.service.decision.transfer.CriteriaOutput;
import ro.johann.dm.test.api.service.decision.transfer.UpdateCriteriaInput;

import javax.inject.Inject;
import java.util.Optional;

public class CriteriaService extends BaseService {

  // TODO extract configs
  private static final String DECISION_MAKER_HOST = "http://localhost:7049";
  private static final String CRITERIA_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/criteria";
  public static final String CRITERIA_BY_ID_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/criteria/{criteriaId}";

  @Inject
  public CriteriaService(HttpService httpService, Storage storage, Mapper mapper) {
    super(httpService, storage, mapper);
  }

  public Optional<CriteriaOutput> addCriteria(String decisionId, AddCriteriaInput input) {
    return post(getCriteriaUri(decisionId), mapper.serialize(input))
      .map(response -> mapper.deserialize(response, CriteriaOutput.class));
  }

  public Optional<CriteriaOutput> updateCriteria(String decisionId, String criteriaId, UpdateCriteriaInput input) {
    return patch(getCriteriaByIdUri(decisionId, criteriaId), mapper.serialize(input))
      .map(response -> mapper.deserialize(response, CriteriaOutput.class));
  }

  public void deleteCriteria(String decisionId, String criteriaId) {
    delete(getCriteriaByIdUri(decisionId, criteriaId));
  }

  private String getCriteriaUri(String decisionId) {
    return CRITERIA_URI
      .replace("{decisionId}", decisionId);
  }

  private String getCriteriaByIdUri(String decisionId, String criteriaId) {
    return CRITERIA_BY_ID_URI
      .replace("{decisionId}", decisionId)
      .replace("{criteriaId}", criteriaId);
  }
}
