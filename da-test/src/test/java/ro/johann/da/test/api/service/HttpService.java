package ro.johann.da.test.api.service;

import lombok.SneakyThrows;

import javax.inject.Inject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

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
  public Response get(String uri, Map<String, String> headers) {
    var requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .GET();
    headers.forEach(requestBuilder::header);
    return doRequest(requestBuilder, headers);
  }

  @SneakyThrows
  public Response post(String uri, byte[] body, Map<String, String> headers) {
    var requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .POST(HttpRequest.BodyPublishers.ofByteArray(body));
    headers.forEach(requestBuilder::header);
    return doRequest(requestBuilder, headers);
  }

  @SneakyThrows
  public Response put(String uri, byte[] body, Map<String, String> headers) {
    var requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .PUT(HttpRequest.BodyPublishers.ofByteArray(body));
    headers.forEach(requestBuilder::header);
    return doRequest(requestBuilder, headers);
  }

  @SneakyThrows
  public Response patch(String uri, byte[] body, Map<String, String> headers) {
    var requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .method("PATCH", HttpRequest.BodyPublishers.ofByteArray(body));
    headers.forEach(requestBuilder::header);
    return doRequest(requestBuilder, headers);
  }

  @SneakyThrows
  public Response delete(String uri, Map<String, String> headers) {
    var requestBuilder = HttpRequest.newBuilder()
      .uri(new URI(uri))
      .DELETE();
    return doRequest(requestBuilder, headers);
  }

  @SneakyThrows
  private Response doRequest(HttpRequest.Builder requestBuilder, Map<String, String> headers) {
    headers.forEach(requestBuilder::header);
    var httpResponse = httpClient.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofByteArray());
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
