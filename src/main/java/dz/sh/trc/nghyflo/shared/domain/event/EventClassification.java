/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EventClassification
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Enum
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.event
 *
 * @Description : Event criticality classification for routing, audit, replay, and retention.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

public enum EventClassification {
    INFORMATIONAL,
    OPERATIONAL,
    COMPLIANCE,
    SECURITY,
    CRITICAL,
    REPLAYABLE
}
