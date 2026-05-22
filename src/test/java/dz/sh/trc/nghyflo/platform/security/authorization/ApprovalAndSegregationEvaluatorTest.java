/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ApprovalAndSegregationEvaluatorTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Verifies approval authority and segregation-of-duty authorization decisions.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

import dz.sh.trc.nghyflo.platform.security.authentication.AuthenticatedActor;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApprovalAndSegregationEvaluatorTest {

    @Test
    void shouldDenySelfApprovalAndAllowDifferentApprover() {
        UserId submitter = UserId.newId();
        AuthenticatedActor sameActor = actor(submitter, Set.of("ROLE_SUPERVISOR"), Set.of("FLOW_READING_APPROVE"));
        AuthenticatedActor differentActor = actor(UserId.newId(), Set.of("ROLE_SUPERVISOR"), Set.of("FLOW_READING_APPROVE"));
        SegregationOfDutyEvaluator evaluator = new SegregationOfDutyEvaluator();

        assertFalse(evaluator.requireDifferentActor(sameActor, submitter, "approve measurement").allowed());
        assertTrue(evaluator.requireDifferentActor(differentActor, submitter, "approve measurement").allowed());
    }

    @Test
    void shouldGrantApprovalAuthorityByPermissionOrFallbackRole() {
        OperationalScope targetScope = new OperationalScope(Set.of("region-1"), Set.of(), Set.of(), Set.of(), Set.of());
        AuthenticatedActor permissionActor = actor(UserId.newId(), Set.of("ROLE_OPERATOR"), Set.of("FLOW_READING_APPROVE"));
        AuthenticatedActor roleActor = actor(UserId.newId(), Set.of("ROLE_APPROVER"), Set.of());
        AuthenticatedActor deniedActor = actor(UserId.newId(), Set.of("ROLE_OPERATOR"), Set.of("FLOW_READING_SUBMIT"));
        ApprovalAuthorityEvaluator evaluator = new ApprovalAuthorityEvaluator();

        assertTrue(evaluator.requireApprovalAuthority(permissionActor, OperationalScope.unrestricted(), targetScope,
                "FLOW_READING_APPROVE", "ROLE_APPROVER").allowed());
        assertTrue(evaluator.requireApprovalAuthority(roleActor, OperationalScope.unrestricted(), targetScope,
                "FLOW_READING_APPROVE", "ROLE_APPROVER").allowed());
        assertFalse(evaluator.requireApprovalAuthority(deniedActor, OperationalScope.unrestricted(), targetScope,
                "FLOW_READING_APPROVE", "ROLE_APPROVER").allowed());
    }

    private AuthenticatedActor actor(UserId userId, Set<String> roles, Set<String> permissions) {
        return new AuthenticatedActor(userId, EmployeeId.newId(), TenantId.newId(), "operator", roles, permissions, false);
    }
}
