package ro.johann.dm.test.api.service.decision;

import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.AddCriteriaInput;
import ro.johann.dm.test.api.service.decision.transfer.CriteriaOutput;

import javax.inject.Inject;
import java.util.Optional;

public class CriteriaService extends BaseService {

    private static final String DECISION_MAKER_HOST = "http://localhost:7023";
    private static final String ADD_CRITERIA_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}/criteria";

    @Inject
    public CriteriaService(HttpService httpService, Storage storage, Mapper mapper) {
        super(httpService, storage, mapper);
    }

    public Optional<CriteriaOutput> addCriteria(String decisionId, AddCriteriaInput input) {
        return post(ADD_CRITERIA_URI.replace("{decisionId}", decisionId), mapper.serialize(input))
                .map(response -> mapper.deserialize(response, CriteriaOutput.class));
    }
}
