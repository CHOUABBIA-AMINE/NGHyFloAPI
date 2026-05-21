/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OutboxEvent
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Record
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.events.outbox
 *
 * @Description : Represents a transactional outbox event pending reliable operational publication.
 *
 */
package dz.sh.trc.nghyflo.platform.events.outbox;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import java.time.Instant;

public record OutboxEvent(
        String id,
        String aggregateType,
        String aggregateId,
        String eventType,
        String payload,
        String status,
        int retryCount,
        Instant occurredAt,
        Instant nextRetryAt,
        CorrelationId correlationId
) {
}
