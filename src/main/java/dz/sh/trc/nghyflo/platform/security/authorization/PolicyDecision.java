/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : PolicyDecision
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Captures explainable RBAC and ABAC authorization decisions for operational actions.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

import java.time.Instant;

public record PolicyDecision(
        boolean allowed,
        String reasonCode,
        String reasonMessage,
        Instant evaluatedAt
) {

    public PolicyDecision {
        if (reasonCode == null || reasonCode.isBlank()) {
            throw new IllegalArgumentException("reasonCode is required");
        }
        if (reasonMessage == null || reasonMessage.isBlank()) {
            throw new IllegalArgumentException("reasonMessage is required");
        }
        if (evaluatedAt == null) {
            throw new IllegalArgumentException("evaluatedAt is required");
        }
    }

    public static PolicyDecision allow(String code, String message) {
        return new PolicyDecision(true, code, message, Instant.now());
    }

    public static PolicyDecision deny(String code, String message) {
        return new PolicyDecision(false, code, message, Instant.now());
    }
}
