package ro.johann.dm.test.api.service.decision;

import lombok.SneakyThrows;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.service.BaseService;
import ro.johann.dm.test.api.service.HttpService;
import ro.johann.dm.test.api.service.Mapper;
import ro.johann.dm.test.api.service.decision.transfer.CreateDecision;
import ro.johann.dm.test.api.service.decision.transfer.Decision;
import ro.johann.dm.test.api.service.decision.transfer.Decisions;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DecisionService extends BaseService {

    // TODO extract to configs
    private static final String DECISION_MAKER_HOST = "http://localhost:7023";
    private static final String CREATE_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions";
    private static final String GET_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}";
    private static final String LIST_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions";
    private static final String DELETE_DECISION_URI = DECISION_MAKER_HOST + "/decision/v1/decisions/{decisionId}";

    private final Mapper mapper;

    @Inject
    public DecisionService(HttpService httpService, Mapper mapper, Storage storage) {
        super(httpService, storage);
        this.mapper = mapper;
    }

    @SneakyThrows
    public Optional<Decision> createDecision(CreateDecision request) {
        return post(CREATE_DECISION_URI, mapper.serialize(request))
                .map(response -> mapper.deserialize(response, Decision.class));
    }

    @SneakyThrows
    public Optional<Decision> getDecision(String decisionId) {
        return get(GET_DECISION_URI.replace("{decisionId}", decisionId))
                .map(response -> mapper.deserialize(response, Decision.class));
    }

    @SneakyThrows
    public Optional<Decisions> listDecisions() {
        return get(LIST_DECISION_URI)
                .map(response -> mapper.deserialize(response, Decisions.class));
    }

    @SneakyThrows
    public void deleteDecision(String decisionId) {
        delete(DELETE_DECISION_URI.replace("{decisionId}", decisionId));
    }

    // TODO extract cleanup logic
    public void cleanUp() {
        listDecisions()
                .map(Decisions::getItems)
                .orElseGet(List::of)
                .stream()
                .map(Decision::getId)
                .forEach(this::deleteDecision);
    }
}
