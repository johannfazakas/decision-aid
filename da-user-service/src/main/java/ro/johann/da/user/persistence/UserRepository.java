package ro.johann.da.user.persistence;

import org.springframework.data.repository.CrudRepository;
import ro.johann.da.user.domain.User;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
  boolean existsByEmail(String email);
}
