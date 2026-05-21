package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.User;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.repository.UserRepository;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.util.*;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> storage = new HashMap<>();
    public User save(User user) { storage.put(user.id().value(), user); return user; }
    public Optional<User> findById(UserId id) { return Optional.ofNullable(storage.get(id.value())); }
    public Optional<User> findByUsername(String username) { return storage.values().stream().filter(u -> u.username().value().equals(username)).findFirst(); }
    public List<User> findAll() { return storage.values().stream().toList(); }
}
