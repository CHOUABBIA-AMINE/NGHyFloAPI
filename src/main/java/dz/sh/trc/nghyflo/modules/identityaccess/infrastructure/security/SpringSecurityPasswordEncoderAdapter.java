/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SpringSecurityPasswordEncoderAdapter
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.security
 *
 * @Description : Spring Security based password encoder adapter for NGHyFlo identity access.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.security;

import dz.sh.trc.nghyflo.modules.identityaccess.application.security.PasswordEncoderPort;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PasswordHash;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SpringSecurityPasswordEncoderAdapter implements PasswordEncoderPort {
    private final PasswordEncoder passwordEncoder;

    public SpringSecurityPasswordEncoderAdapter() {
        this(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    SpringSecurityPasswordEncoderAdapter(PasswordEncoder passwordEncoder) {
        if (passwordEncoder == null) {
            throw new IllegalArgumentException("passwordEncoder is required");
        }
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PasswordHash encode(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("rawPassword is required");
        }
        return new PasswordHash(passwordEncoder.encode(rawPassword));
    }

    @Override
    public boolean matches(String rawPassword, PasswordHash passwordHash) {
        if (rawPassword == null || passwordHash == null) {
            return false;
        }
        return passwordEncoder.matches(rawPassword, passwordHash.value());
    }
}
