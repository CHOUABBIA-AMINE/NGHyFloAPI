/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : UserJpaMapper
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Mapper
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa
 *
 * @Description : Maps identity user domain objects to and from JPA entities.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.User;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PasswordHash;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.RoleCode;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.Username;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.util.Set;
import java.util.stream.Collectors;

public class UserJpaMapper {

    public UserJpaEntity toEntity(User user, Set<RoleJpaEntity> roles) {
        UserJpaEntity entity = new UserJpaEntity(
                user.id().value(),
                user.username().value(),
                user.passwordHash().value(),
                user.createdAt()
        );
        entity.replaceRoles(roles);
        return entity;
    }

    public User toDomain(UserJpaEntity entity) {
        User user = new User(
                UserId.of(entity.id()),
                new Username(entity.username()),
                new PasswordHash(entity.passwordHash()),
                entity.createdAt()
        );
        entity.roles().forEach(role -> user.assignRole(RoleCode.of(role.roleCode())));
        return user;
    }

    public Set<String> roleCodes(User user) {
        return user.roleCodes().stream().map(RoleCode::value).collect(Collectors.toUnmodifiableSet());
    }
}
