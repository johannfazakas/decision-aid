package ro.johann.da.test.api.service;

import lombok.SneakyThrows;

import javax.inject.Inject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {

  private static final String CONTENT_TYPE = "Content-Type";
  private static final String APPLICATION_JSON = "application/json";
  private static final String AUTHORIZATION = "Authorization";
  private static final String LET_ME_IN = "Let me in!";

  private final HttpClient httpClient;

  @Inject
  public HttpService(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  @SneakyThrows
  public Response get(String uri) {
    var httpRequest = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .headers(CONTENT_TYPE, APPLICATION_JSON, AUTHORIZATION, LET_ME_IN)
      .GET()
      .build();
    return doRequest(httpRequest);
  }

  @SneakyThrows
  public Response post(String uri, byte[] body) {
    var httpRequest = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .headers(CONTENT_TYPE, APPLICATION_JSON, AUTHORIZATION, LET_ME_IN)
      .POST(HttpRequest.BodyPublishers.ofByteArray(body))
      .build();
    return doRequest(httpRequest);
  }

  @SneakyThrows
  public Response put(String uri, byte[] body) {
    var httpRequest = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .headers(CONTENT_TYPE, APPLICATION_JSON, AUTHORIZATION, LET_ME_IN)
      .PUT(HttpRequest.BodyPublishers.ofByteArray(body))
      .build();
    return doRequest(httpRequest);
  }

  @SneakyThrows
  public Response patch(String uri, byte[] body) {
    var httpRequest = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .headers(CONTENT_TYPE, APPLICATION_JSON, AUTHORIZATION, LET_ME_IN)
      .method("PATCH", HttpRequest.BodyPublishers.ofByteArray(body))
      .build();
    return doRequest(httpRequest);
  }

  @SneakyThrows
  public Response delete(String uri) {
    var httpRequest = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .headers(CONTENT_TYPE, APPLICATION_JSON, AUTHORIZATION, LET_ME_IN)
      .DELETE()
      .build();
    return doRequest(httpRequest);
  }

  @SneakyThrows
  private Response doRequest(HttpRequest httpRequest) {
    var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
    if (isSuccessful(httpResponse) && httpResponse.body().length > 0) {
      return Response.withBody(httpResponse.statusCode(), httpResponse.body());
    } else {
      return Response.empty(httpResponse.statusCode());
    }
  }

  private boolean isSuccessful(HttpResponse<byte[]> response) {
    return response.statusCode() >= 200 && response.statusCode() < 300;
  }
}
