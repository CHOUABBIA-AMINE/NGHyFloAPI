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

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PermissionCode;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.RoleCode;
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
    void shouldOpenAndCloseUserSession() {
        UserSession session = UserSession.open(UserId.newId());

        assertTrue(session.active());
        session.close(Instant.now().plusSeconds(1));
        assertFalse(session.active());
    }

    @Test
    void shouldDisableServiceAccount() {
        ServiceAccount account = new ServiceAccount(UserId.newId(), "edge-gateway", Instant.now());

        assertTrue(account.enabled());
        account.disable();
        assertFalse(account.enabled());
    }
}
