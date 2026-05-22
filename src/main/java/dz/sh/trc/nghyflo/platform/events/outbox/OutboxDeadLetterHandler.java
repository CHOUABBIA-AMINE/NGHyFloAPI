/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OutboxDeadLetterHandler
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.events.outbox
 *
 * @Description : Handles exhausted outbox events that must be moved to dead-letter storage.
 *
 */
package dz.sh.trc.nghyflo.platform.events.outbox;

public interface OutboxDeadLetterHandler {

    OutboxEvent deadLetter(OutboxEvent event, String reason);
}
