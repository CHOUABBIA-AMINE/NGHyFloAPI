/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EventEnvelope
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.event
 *
 * @Description : Immutable event wrapper carrying event identity, classification, metadata, and payload.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.event;

import dz.sh.trc.nghyflo.shared.domain.model.DomainEvent;
import dz.sh.trc.nghyflo.shared.domain.value.EventId;
import java.time.Instant;

public record EventEnvelope<T extends DomainEvent>(
        EventId eventId,
        String eventName,
        String aggregateType,
        String aggregateId,
        EventType eventType,
        EventClassification classification,
        int schemaVersion,
        Instant occurredAt,
        EventMetadata metadata,
        T payload
) {

    public EventEnvelope {
        if (eventId == null) {
            throw new IllegalArgumentException("eventId is required");
        }
        if (eventName == null || eventName.isBlank()) {
            throw new IllegalArgumentException("eventName is required");
        }
        if (aggregateType == null || aggregateType.isBlank()) {
            throw new IllegalArgumentException("aggregateType is required");
        }
        if (aggregateId == null || aggregateId.isBlank()) {
            throw new IllegalArgumentException("aggregateId is required");
        }
        if (eventType == null) {
            throw new IllegalArgumentException("eventType is required");
        }
        if (classification == null) {
            throw new IllegalArgumentException("classification is required");
        }
        if (schemaVersion <= 0) {
            throw new IllegalArgumentException("schemaVersion must be positive");
        }
        if (occurredAt == null) {
            throw new IllegalArgumentException("occurredAt is required");
        }
        if (metadata == null) {
            throw new IllegalArgumentException("metadata is required");
        }
        if (payload == null) {
            throw new IllegalArgumentException("payload is required");
        }
    }

    public static <T extends DomainEvent> EventEnvelope<T> wrap(
            T payload,
            EventClassification classification,
            EventMetadata metadata
    ) {
        return new EventEnvelope<>(
                payload.eventId(),
                payload.eventName(),
                payload.aggregateType(),
                payload.aggregateId(),
                payload.eventType(),
                classification,
                payload.schemaVersion(),
                payload.occurredAt(),
                metadata,
                payload
        );
    }
}
