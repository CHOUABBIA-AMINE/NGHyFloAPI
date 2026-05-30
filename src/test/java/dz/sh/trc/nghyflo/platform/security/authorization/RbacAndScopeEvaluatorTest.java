/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : RbacAndScopeEvaluatorTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Verifies RBAC, permission, ABAC, and operational scope authorization decisions.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

import dz.sh.trc.nghyflo.platform.security.authentication.AuthenticatedActor;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RbacAndScopeEvaluatorTest {

    @Test
    void shouldGrantAndDenyRoleAndPermissionAccess() {
        AuthenticatedActor actor = actor(Set.of("ROLE_OPERATOR"), Set.of("FLOW_READING_APPROVE"));

        assertTrue(new RbacEvaluator().requireRole(actor, "ROLE_OPERATOR").allowed());
        assertFalse(new RbacEvaluator().requireRole(actor, "ROLE_ADMIN").allowed());
        assertTrue(new PermissionEvaluator().requirePermission(actor, "FLOW_READING_APPROVE").allowed());
        assertFalse(new PermissionEvaluator().requirePermission(actor, "TOPOLOGY_MUTATE").allowed());
    }

    @Test
    void shouldEvaluateOperationalScopeCoverage() {
        OperationalScope granted = new OperationalScope(Set.of("region-1"), Set.of("structure-1"), Set.of(), Set.of(), Set.of());
        OperationalScope required = new OperationalScope(Set.of("region-1"), Set.of(), Set.of(), Set.of(), Set.of());
        OperationalScope denied = new OperationalScope(Set.of("region-2"), Set.of(), Set.of(), Set.of(), Set.of());
        OperationalScopeEvaluator evaluator = new OperationalScopeEvaluator();

        assertTrue(evaluator.requireScope(granted, required).allowed());
        assertFalse(evaluator.requireScope(granted, denied).allowed());
    }

    @Test
    void shouldEvaluateAbacPermissionAndScopeTogether() {
        AuthenticatedActor actor = actor(Set.of("ROLE_SUPERVISOR"), Set.of("FLOW_READING_APPROVE"));
        OperationalScope targetScope = new OperationalScope(Set.of("region-x"), Set.of(), Set.of(), Set.of(), Set.of());

        assertTrue(new AbacEvaluator().evaluate(actor, OperationalScope.unrestricted(), targetScope, "FLOW_READING_APPROVE").allowed());
        assertFalse(new AbacEvaluator().evaluate(actor, OperationalScope.unrestricted(), targetScope, "INCIDENT_CLOSE").allowed());
    }

    private AuthenticatedActor actor(Set<String> roles, Set<String> permissions) {
        return new AuthenticatedActor(UserId.newId(), EmployeeId.newId(), TenantId.newId(), "operator", roles, permissions, false);
    }
}
