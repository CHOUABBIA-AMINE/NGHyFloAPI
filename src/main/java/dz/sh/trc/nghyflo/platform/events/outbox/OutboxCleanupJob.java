/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OutboxCleanupJob
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.events.outbox
 *
 * @Description : Defines cleanup behavior for dispatched outbox events after retention rules allow removal.
 *
 */
package dz.sh.trc.nghyflo.platform.events.outbox;

import java.time.Instant;

public interface OutboxCleanupJob {

    int cleanupDispatchedBefore(Instant cutoff);
}
