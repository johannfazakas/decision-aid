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

    private static final String DECISION_MAKER_HOST = "http://localhost:7023";
    private static final String ADD_CRITERIA_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/criteria";
    public static final String UPDATE_CRITERIA_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/criteria/{criteriaId}";

    @Inject
    public CriteriaService(HttpService httpService, Storage storage, Mapper mapper) {
        super(httpService, storage, mapper);
    }

    public Optional<CriteriaOutput> addCriteria(String decisionId, AddCriteriaInput input) {
        String uri = ADD_CRITERIA_URI
                .replace("{decisionId}", decisionId);
        return post(uri, mapper.serialize(input))
                .map(response -> mapper.deserialize(response, CriteriaOutput.class));
    }

    public Optional<CriteriaOutput> updateCriteria(String decisionId, String criteriaId, UpdateCriteriaInput input) {
        String uri = UPDATE_CRITERIA_URI
                .replace("{decisionId}", decisionId)
                .replace("{criteriaId}", criteriaId);
        return patch(uri, mapper.serialize(input))
                .map(response -> mapper.deserialize(response, CriteriaOutput.class));
    }
}
