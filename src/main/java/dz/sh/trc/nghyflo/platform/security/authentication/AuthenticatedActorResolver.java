/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuthenticatedActorResolver
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authentication
 *
 * @Description : Resolves authenticated NGHyFlo actors exclusively from the Spring Security context.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authentication;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticatedActorResolver {

    /**
     * Request body actor identity is never trusted.
     * Actor identity must come from the authenticated security context only.
     */
    public Optional<AuthenticatedActor> resolveCurrentActor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        if (!(authentication.getPrincipal() instanceof NGHyFloSecurityPrincipal principal)) {
            return Optional.empty();
        }

        Set<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toUnmodifiableSet());

        Set<String> roles = authorities.stream()
                .filter(authority -> authority.startsWith("ROLE_"))
                .collect(Collectors.toUnmodifiableSet());

        Set<String> permissions = authorities.stream()
                .filter(authority -> !authority.startsWith("ROLE_"))
                .collect(Collectors.toUnmodifiableSet());

        return Optional.of(new AuthenticatedActor(
                principal.userId(),
                principal.employeeId(),
                principal.tenantId(),
                principal.username(),
                roles,
                permissions,
                principal.serviceAccount()
        ));
    }

    public AuthenticatedActor requireCurrentActor() {
        return resolveCurrentActor().orElseThrow(() -> new IllegalStateException("Authenticated actor is required"));
    }

    public String currentActorId() {
        return resolveCurrentActor().map(actor -> actor.userId().value()).orElse("anonymous");
    }
}
