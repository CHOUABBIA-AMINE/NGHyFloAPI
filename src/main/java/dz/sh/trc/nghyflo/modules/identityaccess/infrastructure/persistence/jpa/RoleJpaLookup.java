package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa;

import java.util.UUID;

public class RoleJpaLookup {
    private final SpringDataRoleJpaRepository repository;

    public RoleJpaLookup(SpringDataRoleJpaRepository repository) {
        this.repository = repository;
    }

    public RoleJpaEntity getOrCreate(String value) {
        return repository.findByRoleCode(value)
                .orElseGet(() -> repository.save(new RoleJpaEntity(UUID.randomUUID().toString(), value)));
    }
}
