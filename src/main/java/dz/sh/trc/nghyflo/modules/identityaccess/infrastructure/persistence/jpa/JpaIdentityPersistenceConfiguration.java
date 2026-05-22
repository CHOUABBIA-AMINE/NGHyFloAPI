/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : JpaIdentityPersistenceConfiguration
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Configuration
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa
 *
 * @Description : Wires the JPA persistence adapter as the primary identity user repository.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JpaIdentityPersistenceConfiguration {

    @Bean
    public UserJpaMapper userJpaMapper() {
        return new UserJpaMapper();
    }

    @Bean
    public RoleJpaLookup roleJpaLookup(SpringDataRoleJpaRepository roleRepository) {
        return new RoleJpaLookup(roleRepository);
    }

    @Bean
    @Primary
    public UserRepository jpaUserRepository(
            SpringDataUserJpaRepository userRepository,
            RoleJpaLookup roleLookup,
            UserJpaMapper mapper
    ) {
        return new JpaUserRepositoryAdapter(userRepository, roleLookup, mapper);
    }
}
