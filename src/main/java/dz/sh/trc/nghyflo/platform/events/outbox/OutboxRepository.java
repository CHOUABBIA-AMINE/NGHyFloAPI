/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OutboxRepository
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Repository
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.events.outbox
 *
 * @Description : Persistence boundary for transactional outbox events.
 *
 */
package dz.sh.trc.nghyflo.platform.events.outbox;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface OutboxRepository {

    OutboxEvent save(OutboxEvent event);

    Optional<OutboxEvent> findById(String id);

    List<OutboxEvent> findDispatchable(Instant now, int limit);
}
