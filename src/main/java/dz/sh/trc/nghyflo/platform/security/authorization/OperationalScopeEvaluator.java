/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OperationalScopeEvaluator
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Evaluates whether an actor operational scope covers a requested operational action scope.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

public class OperationalScopeEvaluator {

    public PolicyDecision requireScope(OperationalScope grantedScope, OperationalScope requiredScope) {
        if (grantedScope == null) {
            return PolicyDecision.deny("SCOPE_REQUIRED", "Granted operational scope is required");
        }
        if (requiredScope == null) {
            return PolicyDecision.deny("TARGET_SCOPE_REQUIRED", "Required operational scope is required");
        }
        if (grantedScope.covers(requiredScope)) {
            return PolicyDecision.allow("SCOPE_GRANTED", "Granted scope covers required scope");
        }
        return PolicyDecision.deny("SCOPE_DENIED", "Granted scope does not cover required scope");
    }
}
