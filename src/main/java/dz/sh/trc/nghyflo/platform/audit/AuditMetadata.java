/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditMetadata
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Immutable metadata describing who performed an audited NGHyFlo action and why.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;

public record AuditMetadata(
        String auditId,
        UserId actorId,
        TenantId tenantId,
        AuditActionType actionType,
        String targetType,
        String targetId,
        CorrelationId correlationId,
        Instant occurredAt,
        String reasonCode
) {

    public AuditMetadata {
        auditId = requireText(auditId, "auditId");
        if (actorId == null) {
            throw new IllegalArgumentException("actorId is required");
        }
        if (tenantId == null) {
            throw new IllegalArgumentException("tenantId is required");
        }
        if (actionType == null) {
            throw new IllegalArgumentException("actionType is required");
        }
        targetType = requireText(targetType, "targetType");
        targetId = requireText(targetId, "targetId");
        if (correlationId == null) {
            throw new IllegalArgumentException("correlationId is required");
        }
        if (occurredAt == null) {
            throw new IllegalArgumentException("occurredAt is required");
        }
        reasonCode = reasonCode == null ? "UNSPECIFIED" : reasonCode.trim();
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return value.trim();
    }
}
