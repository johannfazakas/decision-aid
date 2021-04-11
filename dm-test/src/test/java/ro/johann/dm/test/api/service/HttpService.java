package ro.johann.dm.test.api.service;

import lombok.SneakyThrows;

import javax.inject.Inject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private final HttpClient httpClient;

    @Inject
    public HttpService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @SneakyThrows
    public Response get(String uri) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .GET()
                .build();
        return doRequest(httpRequest);
    }

    @SneakyThrows
    public Response post(String uri, byte[] body) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofByteArray(body))
                .build();
        return doRequest(httpRequest);
    }

    @SneakyThrows
    public Response put(String uri, byte[] body) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofByteArray(body))
                .build();
        return doRequest(httpRequest);
    }

    @SneakyThrows
    public Response patch(String uri, byte[] body) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .method("PATCH", HttpRequest.BodyPublishers.ofByteArray(body))
                .build();
        return doRequest(httpRequest);
    }

    @SneakyThrows
    public Response delete(String uri) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .headers(CONTENT_TYPE, APPLICATION_JSON)
                .DELETE()
                .build();
        return doRequest(httpRequest);
    }

    @SneakyThrows
    private Response doRequest(HttpRequest httpRequest) {
        var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
        if (httpResponse.body().length > 0) {
            return Response.withBody(httpResponse.statusCode(), httpResponse.body());
        } else {
            return Response.empty(httpResponse.statusCode());
        }
    }
}
