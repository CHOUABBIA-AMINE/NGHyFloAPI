package dz.sh.trc.nghyflo.modules.identityaccess.domain.repository;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.User;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UserId id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
}
