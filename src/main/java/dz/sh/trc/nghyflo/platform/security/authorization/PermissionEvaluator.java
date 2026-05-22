/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : PermissionEvaluator
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Evaluates direct permission ownership for authenticated NGHyFlo actors.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

import dz.sh.trc.nghyflo.platform.security.authentication.AuthenticatedActor;

public class PermissionEvaluator {

    public PolicyDecision requirePermission(AuthenticatedActor actor, String permission) {
        if (actor == null) {
            return PolicyDecision.deny("ACTOR_REQUIRED", "Authenticated actor is required");
        }
        if (permission == null || permission.isBlank()) {
            return PolicyDecision.deny("PERMISSION_REQUIRED", "Permission is required");
        }
        if (actor.hasPermission(permission)) {
            return PolicyDecision.allow("PERMISSION_GRANTED", "Actor has required permission");
        }
        return PolicyDecision.deny("PERMISSION_DENIED", "Actor does not have required permission");
    }
}
