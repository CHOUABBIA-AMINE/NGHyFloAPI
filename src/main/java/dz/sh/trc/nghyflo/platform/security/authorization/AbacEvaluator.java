/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AbacEvaluator
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Evaluates attribute-based operational authorization decisions using actor and target scopes.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

import dz.sh.trc.nghyflo.platform.security.authentication.AuthenticatedActor;

public class AbacEvaluator {
    private final OperationalScopeEvaluator scopeEvaluator;

    public AbacEvaluator() {
        this(new OperationalScopeEvaluator());
    }

    public AbacEvaluator(OperationalScopeEvaluator scopeEvaluator) {
        this.scopeEvaluator = scopeEvaluator;
    }

    public PolicyDecision evaluate(
            AuthenticatedActor actor,
            OperationalScope actorScope,
            OperationalScope targetScope,
            String requiredPermission
    ) {
        if (actor == null) {
            return PolicyDecision.deny("ACTOR_REQUIRED", "Authenticated actor is required");
        }
        if (requiredPermission != null && !requiredPermission.isBlank() && !actor.hasPermission(requiredPermission)) {
            return PolicyDecision.deny("PERMISSION_DENIED", "Actor does not have required permission");
        }
        PolicyDecision scopeDecision = scopeEvaluator.requireScope(actorScope, targetScope);
        if (!scopeDecision.allowed()) {
            return scopeDecision;
        }
        return PolicyDecision.allow("ABAC_GRANTED", "Actor attributes satisfy operational policy");
    }
}
