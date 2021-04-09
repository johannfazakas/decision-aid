package ro.johann.dm.test.api.decision;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ro.johann.dm.test.api.decision.transfer.CreateDecision;
import ro.johann.dm.test.api.decision.transfer.Decision;
import ro.johann.dm.test.api.decision.transfer.Response;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DecisionService {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String CREATE_DECISION_URI = "http://localhost:7023/decision/v1/decisions";

    // TODO inject
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public Response<Decision> createDecision(CreateDecision createDecision) {
        var request = HttpRequest.newBuilder()
                .uri(new URI(CREATE_DECISION_URI))
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(createDecision)))
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var decision = objectMapper.readValue(response.body(), Decision.class);
        return new Response<>(response.statusCode(), decision);
    }
}
