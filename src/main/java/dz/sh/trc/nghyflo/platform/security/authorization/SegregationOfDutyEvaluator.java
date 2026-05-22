/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SegregationOfDutyEvaluator
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Enforces no self-approval and maker-checker governance for operational workflows.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

import dz.sh.trc.nghyflo.platform.security.authentication.AuthenticatedActor;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;

public class SegregationOfDutyEvaluator {

    public PolicyDecision requireDifferentActor(AuthenticatedActor actor, UserId originalActorId, String actionName) {
        if (actor == null) {
            return PolicyDecision.deny("ACTOR_REQUIRED", "Authenticated actor is required");
        }
        if (originalActorId == null) {
            return PolicyDecision.deny("ORIGINAL_ACTOR_REQUIRED", "Original actor is required");
        }
        if (actor.userId().equals(originalActorId)) {
            return PolicyDecision.deny("SELF_APPROVAL_DENIED", "Actor cannot perform maker-checker action on own submission");
        }
        return PolicyDecision.allow("SEGREGATION_OF_DUTY_GRANTED", "Actor is different from original actor for " + actionName);
    }
}
