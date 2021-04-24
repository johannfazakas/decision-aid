package ro.johann.da.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import lombok.SneakyThrows;

public class Mapper {

  private final ObjectMapper objectMapper;

  @Inject
  public Mapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @SneakyThrows
  public byte[] serialize(Object value) {
    return objectMapper.writeValueAsBytes(value);
  }

  @SneakyThrows
  public <T> T deserialize(byte[] bytes, Class<T> type) {
    return objectMapper.readValue(bytes, type);
  }
}
