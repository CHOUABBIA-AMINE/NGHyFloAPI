/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EventType
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Enum
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.event
 *
 * @Description : Canonical event families used across NGHyFlo bounded contexts.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

public enum EventType {
    DOMAIN,
    OPERATIONAL,
    WORKFLOW,
    TELEMETRY,
    SECURITY,
    INTEGRATION,
    AUDIT
}
