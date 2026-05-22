/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : IdentityDomainModelTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.domain.model
 *
 * @Description : Verifies identity-access domain value and lifecycle models.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.domain.model;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PasswordHash;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PermissionCode;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.RoleCode;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.SessionId;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.Username;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdentityDomainModelTest {

    @Test
    void shouldNormalizeRoleAndPermissionCodes() {
        assertEquals("ROLE_OPERATOR", RoleCode.of("role_operator").value());
        assertEquals("FLOW_APPROVE", PermissionCode.of("flow_approve").value());
        assertThrows(IllegalArgumentException.class, () -> RoleCode.of(" "));
        assertThrows(IllegalArgumentException.class, () -> PermissionCode.of(" "));
    }

    @Test
    void shouldAssignTypedRoleToUserAndKeepStringCompatibility() {
        User user = new User(UserId.newId(), new Username("operator.one"), new PasswordHash("{bcrypt}encoded"), Instant.now());

        user.assignRole(RoleCode.of("role_operator"));

        assertTrue(user.hasRole(RoleCode.of("ROLE_OPERATOR")));
        assertTrue(user.roles().contains("ROLE_OPERATOR"));
    }

    @Test
    void shouldAttachCodesToRoleAndGroup() {
        Role role = new Role(RoleCode.of("role_supervisor"), "Supervisor");
        role.addPermission(PermissionCode.of("flow_approve"));
        Group group = new Group("pipeline-ops");
        group.addMember(UserId.newId());
        group.addRole(role.code());

        assertTrue(role.hasPermission(PermissionCode.of("FLOW_APPROVE")));
        assertTrue(group.roles().contains(RoleCode.of("ROLE_SUPERVISOR")));
    }

    @Test
    void shouldOpenAndCloseUserSession() {
        UserSession session = UserSession.open(UserId.newId());

        assertTrue(session.active());
        session.close(Instant.now().plusSeconds(1));
        assertFalse(session.active());
    }

    @Test
    void shouldHandleRefreshTokenLifecycle() {
        RefreshToken token = new RefreshToken("token-hash", SessionId.newId(), Instant.now().plusSeconds(60));

        assertTrue(token.usableAt(Instant.now()));
        token.revoke();
        assertFalse(token.usableAt(Instant.now()));
    }

    @Test
    void shouldDisableServiceAccount() {
        ServiceAccount account = new ServiceAccount(UserId.newId(), "edge-gateway", Instant.now());

        assertTrue(account.enabled());
        account.disable();
        assertFalse(account.enabled());
    }

    @Test
    void shouldEvaluateCredentialPolicyAndAccessLogEntry() {
        CredentialPolicy policy = new CredentialPolicy(12, true);
        AccessLogEntry entry = new AccessLogEntry(new Username("operator.one"), Instant.now(), true, "127.0.0.1");

        assertTrue(policy.accepts("long-secret-value"));
        assertFalse(policy.accepts("short"));
        assertTrue(entry.accepted());
        assertEquals("127.0.0.1", entry.sourceAddress());
    }
}
