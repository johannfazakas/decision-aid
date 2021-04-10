package ro.johann.dm.test.api.decision;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ro.johann.dm.test.api.common.Storage;
import ro.johann.dm.test.api.decision.transfer.CreateDecision;
import ro.johann.dm.test.api.decision.transfer.Decision;
import ro.johann.dm.test.api.decision.transfer.Decisions;

import javax.inject.Inject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public class DecisionService {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CREATE_DECISION_URI = "http://localhost:7023/decision/v1/decisions";
    private static final String GET_DECISION_URI = "http://localhost:7023/decision/v1/decisions/{decisionId}";
    private static final String LIST_DECISION_URI = "http://localhost:7023/decision/v1/decisions";
    private static final String DELETE_DECISION_URI = "http://localhost:7023/decision/v1/decisions/{decisionId}";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final Storage storage;

    @Inject
    public DecisionService(HttpClient httpClient, ObjectMapper objectMapper, Storage storage) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.storage = storage;
    }

    @SneakyThrows
    public Optional<Decision> createDecision(CreateDecision createDecision) {
        var request = HttpRequest.newBuilder()
                .uri(new URI(CREATE_DECISION_URI))
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(createDecision)))
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        this.storage.setResponseStatusCode(response.statusCode());
        try {
            return Optional.of(objectMapper.readValue(response.body(), Decision.class));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    public Optional<Decision> getDecision(String decisionId) {
        var uri = GET_DECISION_URI
                .replace("{decisionId}", decisionId);
        var request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .GET()
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        this.storage.setResponseStatusCode(response.statusCode());
        try {
            return Optional.of(objectMapper.readValue(response.body(), Decision.class));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    public Optional<Decisions> listDecisions() {
        var request = HttpRequest.newBuilder()
                .uri(new URI(LIST_DECISION_URI))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .GET()
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        this.storage.setResponseStatusCode(response.statusCode());
        try {
            return Optional.of(objectMapper.readValue(response.body(), Decisions.class));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    public void deleteDecision(String decisionId) {
        var uri = DELETE_DECISION_URI
                .replace("{decisionId}", decisionId);
        var request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .DELETE()
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
        this.storage.setResponseStatusCode(response.statusCode());
    }

    public void cleanUp() {
        listDecisions()
                .map(Decisions::getItems)
                .orElseGet(List::of)
                .stream()
                .map(Decision::getId)
                .forEach(this::deleteDecision);
    }
}
