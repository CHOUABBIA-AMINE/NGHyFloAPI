/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : DomainEvent
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Interface
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.model
 *
 * @Description : Base immutable domain event contract for operationally traceable event sourcing.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.model;

import dz.sh.trc.nghyflo.shared.domain.event.EventType;
import dz.sh.trc.nghyflo.shared.domain.value.CausationId;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.EventId;
import java.time.Instant;

public interface DomainEvent {
    EventId eventId();
    Instant occurredAt();
    String aggregateId();
    EventType eventType();
    CorrelationId correlationId();
    CausationId causationId();
}
