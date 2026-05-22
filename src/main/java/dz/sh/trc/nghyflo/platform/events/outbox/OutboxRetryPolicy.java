/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OutboxRetryPolicy
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.events.outbox
 *
 * @Description : Determines retry behavior for failed transactional outbox event delivery.
 *
 */
package dz.sh.trc.nghyflo.platform.events.outbox;

import java.time.Duration;

public interface OutboxRetryPolicy {

    boolean canRetry(OutboxEvent event);

    Duration nextRetryDelay(OutboxEvent event);
}
