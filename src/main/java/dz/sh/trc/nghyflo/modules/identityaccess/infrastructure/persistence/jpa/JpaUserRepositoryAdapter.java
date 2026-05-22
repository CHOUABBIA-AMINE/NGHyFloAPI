package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.User;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.repository.UserRepository;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

public class JpaUserRepositoryAdapter implements UserRepository {
    private final SpringDataUserJpaRepository users;
    private final RoleJpaLookup roleLookup;
    private final UserJpaMapper mapper;

    public JpaUserRepositoryAdapter(SpringDataUserJpaRepository users, RoleJpaLookup roleLookup, UserJpaMapper mapper) {
        this.users = users;
        this.roleLookup = roleLookup;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public User save(User user) {
        Set<RoleJpaEntity> roles = mapper.roleCodes(user).stream()
                .map(roleLookup::getOrCreate)
                .collect(Collectors.toUnmodifiableSet());
        return mapper.toDomain(users.save(mapper.toEntity(user, roles)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(UserId id) {
        return users.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return users.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return users.findAll().stream().map(mapper::toDomain).toList();
    }
}
