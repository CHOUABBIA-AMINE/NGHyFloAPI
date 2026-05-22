/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : UserJpaMapperTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa
 *
 * @Description : Verifies identity user domain/JPA mapping including typed role codes.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.User;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PasswordHash;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.RoleCode;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.Username;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserJpaMapperTest {

    private final UserJpaMapper mapper = new UserJpaMapper();

    @Test
    void shouldMapDomainUserToJpaEntity() {
        User user = new User(
                UserId.newId(),
                new Username("operator.one"),
                new PasswordHash("{bcrypt}encoded-value"),
                Instant.parse("2026-05-22T10:00:00Z")
        );
        user.assignRole(RoleCode.of("role_operator"));
        RoleJpaEntity role = new RoleJpaEntity("role-id", "ROLE_OPERATOR");

        UserJpaEntity entity = mapper.toEntity(user, Set.of(role));

        assertEquals(user.id().value(), entity.id());
        assertEquals("operator.one", entity.username());
        assertEquals("{bcrypt}encoded-value", entity.passwordHash());
        assertEquals(1, entity.roles().size());
    }

    @Test
    void shouldMapJpaEntityToDomainUser() {
        UserJpaEntity entity = new UserJpaEntity(
                UserId.newId().value(),
                "operator.two",
                "{bcrypt}encoded-value",
                Instant.parse("2026-05-22T10:00:00Z")
        );
        entity.replaceRoles(Set.of(new RoleJpaEntity("role-id", "ROLE_SUPERVISOR")));

        User user = mapper.toDomain(entity);

        assertEquals("operator.two", user.username().value());
        assertTrue(user.hasRole(RoleCode.of("role_supervisor")));
        assertTrue(user.roles().contains("ROLE_SUPERVISOR"));
    }
}
