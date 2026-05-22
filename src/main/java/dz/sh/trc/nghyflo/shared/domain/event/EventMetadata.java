/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EventMetadata
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.event
 *
 * @Description : Governance and lineage metadata attached to NGHyFlo events.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

import dz.sh.trc.nghyflo.shared.domain.value.CausationId;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;

public record EventMetadata(
        UserId actorId,
        TenantId tenantId,
        CorrelationId correlationId,
        CausationId causationId,
        String sourceModule,
        int schemaVersion,
        Instant recordedAt
) {

    public EventMetadata {
        if (actorId == null || tenantId == null || correlationId == null || causationId == null) {
            throw new IllegalArgumentException("Required metadata is missing");
        }
        if (sourceModule == null || sourceModule.isBlank()) {
            throw new IllegalArgumentException("sourceModule is required");
        }
        if (schemaVersion <= 0) {
            throw new IllegalArgumentException("schemaVersion must be positive");
        }
        if (recordedAt == null) {
            throw new IllegalArgumentException("recordedAt is required");
        }
    }

    public EventMetadata(
            UserId actorId,
            TenantId tenantId,
            CorrelationId correlationId,
            CausationId causationId,
            String sourceModule,
            int schemaVersion
    ) {
        this(actorId, tenantId, correlationId, causationId, sourceModule, schemaVersion, Instant.now());
    }

    public static EventMetadata of(
            UserId actorId,
            TenantId tenantId,
            CorrelationId correlationId,
            CausationId causationId,
            String sourceModule
    ) {
        return new EventMetadata(actorId, tenantId, correlationId, causationId, sourceModule, 1, Instant.now());
    }
}
