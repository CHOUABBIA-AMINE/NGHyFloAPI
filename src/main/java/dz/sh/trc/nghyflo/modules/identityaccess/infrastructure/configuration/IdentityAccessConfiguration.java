/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : IdentityAccessConfiguration
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Configuration
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.configuration
 *
 * @Description : Wires identity-access application services and infrastructure adapters for the current skeleton.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.configuration;

import dz.sh.trc.nghyflo.modules.identityaccess.application.security.PasswordEncoderPort;
import dz.sh.trc.nghyflo.modules.identityaccess.application.service.IdentityApplicationService;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.repository.UserRepository;
import dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.InMemoryUserRepository;
import dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.security.SpringSecurityPasswordEncoderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityAccessConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public PasswordEncoderPort passwordEncoderPort() {
        return new SpringSecurityPasswordEncoderAdapter();
    }

    @Bean
    public IdentityApplicationService identityApplicationService(
            UserRepository userRepository,
            PasswordEncoderPort passwordEncoderPort
    ) {
        return new IdentityApplicationService(userRepository, passwordEncoderPort);
    }
}
