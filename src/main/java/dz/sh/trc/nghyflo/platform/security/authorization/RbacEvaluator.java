/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : RbacEvaluator
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Evaluates role-based access decisions for NGHyFlo operational actors.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

import dz.sh.trc.nghyflo.platform.security.authentication.AuthenticatedActor;

public class RbacEvaluator {

    public PolicyDecision requireRole(AuthenticatedActor actor, String role) {
        if (actor == null) {
            return PolicyDecision.deny("ACTOR_REQUIRED", "Authenticated actor is required");
        }
        if (role == null || role.isBlank()) {
            return PolicyDecision.deny("ROLE_REQUIRED", "Role is required");
        }
        if (actor.hasRole(role)) {
            return PolicyDecision.allow("ROLE_GRANTED", "Actor has required role");
        }
        return PolicyDecision.deny("ROLE_DENIED", "Actor does not have required role");
    }
}
