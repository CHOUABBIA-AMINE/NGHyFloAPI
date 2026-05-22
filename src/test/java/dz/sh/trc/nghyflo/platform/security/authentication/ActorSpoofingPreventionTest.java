/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ActorSpoofingPreventionTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authentication
 *
 * @Description : Verifies authenticated actor identity is resolved only from trusted security context.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authentication;

import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActorSpoofingPreventionTest {

    private final AuthenticatedActorResolver resolver = new AuthenticatedActorResolver();

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldResolveActorFromTrustedSecurityPrincipalOnly() {
        UserId userId = UserId.newId();
        EmployeeId employeeId = EmployeeId.newId();
        TenantId tenantId = TenantId.newId();
        NGHyFloSecurityPrincipal principal = new NGHyFloSecurityPrincipal(
                userId,
                employeeId,
                tenantId,
                "operator.one",
                false
        );
        TestingAuthenticationToken authentication = new TestingAuthenticationToken(
                principal,
                "ignored",
                List.of(new SimpleGrantedAuthority("ROLE_OPERATOR"), new SimpleGrantedAuthority("FLOW_READING_SUBMIT"))
        );
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthenticatedActor actor = resolver.requireCurrentActor();

        assertEquals(userId, actor.userId());
        assertEquals(employeeId, actor.employeeId());
        assertEquals(tenantId, actor.tenantId());
        assertEquals("operator.one", actor.username());
        assertTrue(actor.hasRole("ROLE_OPERATOR"));
        assertTrue(actor.hasPermission("FLOW_READING_SUBMIT"));
    }

    @Test
    void shouldNotResolveActorFromPlainAuthenticationName() {
        TestingAuthenticationToken authentication = new TestingAuthenticationToken(
                "spoofed-user-id-from-client",
                "ignored",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        assertFalse(resolver.resolveCurrentActor().isPresent());
        assertThrows(IllegalStateException.class, resolver::requireCurrentActor);
        assertEquals("anonymous", resolver.currentActorId());
    }

    @Test
    void shouldIgnoreRequestBodyActorIdentityWhenSecurityContextHasDifferentActor() {
        UserId authenticatedUserId = UserId.newId();
        String spoofedRequestBodyActorId = UserId.newId().value();
        NGHyFloSecurityPrincipal principal = new NGHyFloSecurityPrincipal(
                authenticatedUserId,
                EmployeeId.newId(),
                TenantId.newId(),
                "trusted.actor",
                false
        );
        TestingAuthenticationToken authentication = new TestingAuthenticationToken(
                principal,
                "ignored",
                List.of(new SimpleGrantedAuthority("ROLE_SUPERVISOR"))
        );
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthenticatedActor actor = resolver.requireCurrentActor();

        assertEquals(authenticatedUserId.value(), actor.userId().value());
        assertFalse(spoofedRequestBodyActorId.equals(actor.userId().value()));
    }
}
