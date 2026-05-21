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
 * @Description : Metadata for governed event propagation.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

import java.time.Instant;
public record EventMetadata(String eventId, String correlationId, String causationId, Instant occurredAt) {}
