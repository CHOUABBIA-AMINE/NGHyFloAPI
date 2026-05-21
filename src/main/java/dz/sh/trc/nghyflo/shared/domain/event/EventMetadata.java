/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EventMetadata
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.event
 *
 * @Description : Metadata envelope for governance and lineage of cross-module events.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

import dz.sh.trc.nghyflo.shared.domain.value.CausationId;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;

public record EventMetadata(UserId actorId, TenantId tenantId, CorrelationId correlationId, CausationId causationId,
                            String sourceModule, int schemaVersion) {
    public EventMetadata {
        if (actorId == null || tenantId == null || correlationId == null || causationId == null) throw new IllegalArgumentException("Required metadata is missing");
        if (sourceModule == null || sourceModule.isBlank()) throw new IllegalArgumentException("sourceModule is required");
        if (schemaVersion <= 0) throw new IllegalArgumentException("schemaVersion must be positive");
    }
}
