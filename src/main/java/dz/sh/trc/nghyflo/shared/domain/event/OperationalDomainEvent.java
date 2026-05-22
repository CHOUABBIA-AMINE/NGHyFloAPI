/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OperationalDomainEvent
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.event
 *
 * @Description : Marker contract for operational events affecting published industrial state.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

import dz.sh.trc.nghyflo.shared.domain.model.DomainEvent;

public interface OperationalDomainEvent extends DomainEvent {

    @Override
    default EventType eventType() {
        return EventType.OPERATIONAL;
    }
}
