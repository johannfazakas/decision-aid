package ro.johann.da.test.api.service;

import ro.johann.da.test.api.common.Storage;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;

public class BaseService {

  protected final HttpService httpService;
  protected final Storage storage;
  protected final Mapper mapper;
  protected final Properties properties;

  public BaseService(HttpService httpService, Storage storage, Mapper mapper, Properties properties) {
    this.httpService = httpService;
    this.storage = storage;
    this.mapper = mapper;
    this.properties = properties;
  }

  protected Optional<byte[]> get(String uri) {
    return doRequest(() -> httpService.get(uri));
  }

  protected Optional<byte[]> post(String uri, byte[] body) {
    return doRequest(() -> httpService.post(uri, body));
  }

  protected Optional<byte[]> put(String uri, byte[] body) {
    return doRequest(() -> httpService.put(uri, body));
  }

  protected Optional<byte[]> patch(String uri, byte[] body) {
    return doRequest(() -> httpService.patch(uri, body));
  }

  protected Optional<byte[]> delete(String uri) {
    return doRequest(() -> httpService.delete(uri));
  }

  private Optional<byte[]> doRequest(Supplier<Response> request) {
    var response = request.get();
    this.storage.setResponseStatusCode(response.getStatusCode());
    return response.getBody();
  }

  protected String getUrl(String urlKey, Map<String, String> pathParams) {
    var url = properties.getProperty(urlKey);
    for (String paramKey : pathParams.keySet()) {
      url = url.replace(String.format("{%s}", paramKey), pathParams.get(paramKey));
    }
    return url;
  }
}
