/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : WorkflowDomainEvent
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.event
 *
 * @Description : Marker contract for workflow governance and approval transition events.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

import dz.sh.trc.nghyflo.shared.domain.model.DomainEvent;

public interface WorkflowDomainEvent extends DomainEvent {

    @Override
    default EventType eventType() {
        return EventType.WORKFLOW;
    }
}
