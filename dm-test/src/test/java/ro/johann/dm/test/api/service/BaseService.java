package ro.johann.dm.test.api.service;

import ro.johann.dm.test.api.common.Storage;

import java.util.Optional;
import java.util.function.Supplier;

public class BaseService {

    protected final HttpService httpService;
    protected final Storage storage;

    public BaseService(HttpService httpService, Storage storage) {
        this.httpService = httpService;
        this.storage = storage;
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

    protected Optional<byte[]> delete(String uri) {
        return doRequest(() -> httpService.delete(uri));
    }

    private Optional<byte[]> doRequest(Supplier<Response> request) {
        var response = request.get();
        this.storage.setResponseStatusCode(response.getStatusCode());
        return response.getBody();
    }
}
