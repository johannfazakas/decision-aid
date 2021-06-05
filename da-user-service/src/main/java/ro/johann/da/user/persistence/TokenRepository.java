package ro.johann.da.user.persistence;

import org.springframework.data.repository.CrudRepository;
import ro.johann.da.user.domain.Token;

import java.util.UUID;

public interface TokenRepository extends CrudRepository<Token, UUID> {
}
