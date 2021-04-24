package ro.johann.da.test.api.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@FieldDefaults(level = PRIVATE)
public class Response {
  int statusCode;
  byte[] body;

  public static Response empty(int statusCode) {
    return new Response(statusCode, null);
  }

  public static Response withBody(int statusCode, byte[] body) {
    return new Response(statusCode, body);
  }

  public Optional<byte[]> getBody() {
    return Optional.ofNullable(body);
  }
}
