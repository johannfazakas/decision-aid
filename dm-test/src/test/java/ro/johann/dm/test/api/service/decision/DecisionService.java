package ro.johann.dm.test.api.service.decision;

import lombok.SneakyThrows;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.CreateDecisionInput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionOutput;
import ro.johann.dm.test.api.service.decision.transfer.DecisionsOutput;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DecisionService extends BaseService {

    // TODO extract to configs
    private static final String DECISION_MAKER_HOST = "http://localhost:7032";
    private static final String CREATE_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions";
    private static final String GET_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}";
    private static final String LIST_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions";
    private static final String DELETE_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}";

    @Inject
    public DecisionService(HttpService httpService, Mapper mapper, Storage storage) {
        super(httpService, storage, mapper);
    }

    @SneakyThrows
    public Optional<DecisionOutput> createDecision(CreateDecisionInput request) {
        return post(CREATE_DECISION_URI, mapper.serialize(request))
                .map(response -> mapper.deserialize(response, DecisionOutput.class));
    }

    @SneakyThrows
    public Optional<DecisionOutput> getDecision(String decisionId) {
        return get(GET_DECISION_URI.replace("{decisionId}", decisionId))
                .map(response -> mapper.deserialize(response, DecisionOutput.class));
    }

    @SneakyThrows
    public Optional<DecisionsOutput> listDecisions() {
        return get(LIST_DECISION_URI)
                .map(response -> mapper.deserialize(response, DecisionsOutput.class));
    }

    @SneakyThrows
    public void deleteDecision(String decisionId) {
        delete(DELETE_DECISION_URI.replace("{decisionId}", decisionId));
    }

    // TODO extract cleanup logic
    public void cleanUp() {
        listDecisions()
                .map(DecisionsOutput::getItems)
                .orElseGet(List::of)
                .stream()
                .map(DecisionOutput::getId)
                .forEach(this::deleteDecision);
    }
}
