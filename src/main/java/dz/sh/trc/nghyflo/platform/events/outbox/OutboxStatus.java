/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OutboxStatus
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Enum
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.events.outbox
 *
 * @Description : Lifecycle status for transactional outbox events awaiting reliable publication.
 *
 */
package dz.sh.trc.nghyflo.platform.events.outbox;

public enum OutboxStatus {
    PENDING,
    DISPATCHED,
    FAILED,
    DEAD_LETTERED
}
