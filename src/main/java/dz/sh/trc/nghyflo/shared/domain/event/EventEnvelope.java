/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EventEnvelope
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.event
 *
 * @Description : Envelope wrapper for event payload exchange.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

public record EventEnvelope<T>(EventType eventType, EventClassification classification, EventMetadata metadata, T payload) {}
