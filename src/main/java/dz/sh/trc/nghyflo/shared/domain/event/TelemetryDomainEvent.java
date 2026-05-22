/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : TelemetryDomainEvent
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.event
 *
 * @Description : Marker contract for telemetry ingestion and quality events.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

import dz.sh.trc.nghyflo.shared.domain.model.DomainEvent;

public interface TelemetryDomainEvent extends DomainEvent {

    @Override
    default EventType eventType() {
        return EventType.TELEMETRY;
    }
}
