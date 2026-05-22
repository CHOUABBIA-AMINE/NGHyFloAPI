/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ApprovalAuthorityEvaluator
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Evaluates whether an authenticated actor has authority to approve governed operational actions.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

import dz.sh.trc.nghyflo.platform.security.authentication.AuthenticatedActor;

public class ApprovalAuthorityEvaluator {
    private final PermissionEvaluator permissionEvaluator;
    private final RbacEvaluator rbacEvaluator;
    private final OperationalScopeEvaluator scopeEvaluator;

    public ApprovalAuthorityEvaluator() {
        this(new PermissionEvaluator(), new RbacEvaluator(), new OperationalScopeEvaluator());
    }

    public ApprovalAuthorityEvaluator(
            PermissionEvaluator permissionEvaluator,
            RbacEvaluator rbacEvaluator,
            OperationalScopeEvaluator scopeEvaluator
    ) {
        this.permissionEvaluator = permissionEvaluator;
        this.rbacEvaluator = rbacEvaluator;
        this.scopeEvaluator = scopeEvaluator;
    }

    public PolicyDecision requireApprovalAuthority(
            AuthenticatedActor actor,
            OperationalScope actorScope,
            OperationalScope targetScope,
            String approvalPermission,
            String fallbackApprovalRole
    ) {
        PolicyDecision permissionDecision = permissionEvaluator.requirePermission(actor, approvalPermission);
        PolicyDecision roleDecision = rbacEvaluator.requireRole(actor, fallbackApprovalRole);
        if (!permissionDecision.allowed() && !roleDecision.allowed()) {
            return PolicyDecision.deny("APPROVAL_AUTHORITY_DENIED", "Actor lacks approval permission or fallback approval role");
        }
        PolicyDecision scopeDecision = scopeEvaluator.requireScope(actorScope, targetScope);
        if (!scopeDecision.allowed()) {
            return scopeDecision;
        }
        return PolicyDecision.allow("APPROVAL_AUTHORITY_GRANTED", "Actor has approval authority for target scope");
    }
}
