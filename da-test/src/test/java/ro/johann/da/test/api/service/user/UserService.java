package ro.johann.da.test.api.service.user;

import com.google.inject.Inject;
import ro.johann.da.test.api.common.Storage;
import ro.johann.da.test.api.service.BaseService;
import ro.johann.da.test.api.service.HttpService;
import ro.johann.da.test.api.service.Mapper;
import ro.johann.da.test.api.service.user.transfer.RegisterInput;
import ro.johann.da.test.api.service.user.transfer.UserOutput;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static java.util.Collections.emptyMap;

public class UserService extends BaseService {

  @Inject
  public UserService(HttpService httpService, Storage storage, Mapper mapper, Properties properties) {
    super(httpService, storage, mapper, properties);
  }

  public Optional<UserOutput> registerUser(RegisterInput input) {
    var url = getUrl("userApi.usersUrl", emptyMap());
    return post(url, mapper.serialize(input)).map(output -> mapper.deserialize(output, UserOutput.class));
  }

  public void deleteUser(String userId) {
    var url = getUrl("userApi.userByIdUrl", Map.of("userId", userId));
    delete(url);
  }

  // TODO extract cleanup logic
  public void cleanUp() {
    Optional.ofNullable(storage.getUser())
      .map(UserOutput::getId)
      .ifPresent(this::deleteUser);
  }
}
