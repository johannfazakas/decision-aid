package ro.johann.dm.test.api.decision.transfer;

import lombok.Value;

@Value
public class Response<T> {
    int statusCode;
    T object;
}
