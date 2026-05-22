/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OutboxEvent
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.events.outbox
 *
 * @Description : Represents a validated transactional outbox event pending reliable publication.
 *
 */
package dz.sh.trc.nghyflo.platform.events.outbox;

import dz.sh.trc.nghyflo.shared.domain.event.EventMetadata;
import dz.sh.trc.nghyflo.shared.domain.model.DomainEvent;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.EventId;
import java.time.Duration;
import java.time.Instant;

public record OutboxEvent(
        String id,
        String aggregateType,
        String aggregateId,
        String eventType,
        String payload,
        OutboxStatus status,
        int retryCount,
        Instant occurredAt,
        Instant nextRetryAt,
        CorrelationId correlationId,
        String errorReason
) {

    public OutboxEvent {
        id = requireText(id, "id");
        aggregateType = requireText(aggregateType, "aggregateType");
        aggregateId = requireText(aggregateId, "aggregateId");
        eventType = requireText(eventType, "eventType");
        payload = requireText(payload, "payload");
        if (status == null) {
            throw new IllegalArgumentException("status is required");
        }
        if (retryCount < 0) {
            throw new IllegalArgumentException("retryCount cannot be negative");
        }
        if (occurredAt == null) {
            throw new IllegalArgumentException("occurredAt is required");
        }
        if (correlationId == null) {
            throw new IllegalArgumentException("correlationId is required");
        }
        if (status == OutboxStatus.PENDING && nextRetryAt == null) {
            nextRetryAt = occurredAt;
        }
    }

    public OutboxEvent(
            String id,
            String aggregateType,
            String aggregateId,
            String eventType,
            String payload,
            String status,
            int retryCount,
            Instant occurredAt,
            Instant nextRetryAt,
            CorrelationId correlationId
    ) {
        this(id, aggregateType, aggregateId, eventType, payload,
                OutboxStatus.valueOf(requireText(status, "status")), retryCount,
                occurredAt, nextRetryAt, correlationId, null);
    }

    public static OutboxEvent pending(
            String aggregateType,
            String aggregateId,
            String eventType,
            String payload,
            CorrelationId correlationId
    ) {
        Instant now = Instant.now();
        return new OutboxEvent(EventId.newId().value(), aggregateType, aggregateId, eventType, payload,
                OutboxStatus.PENDING, 0, now, now, correlationId, null);
    }

    public static OutboxEvent from(DomainEvent event, EventMetadata metadata, String payload) {
        if (event == null) {
            throw new IllegalArgumentException("event is required");
        }
        if (metadata == null) {
            throw new IllegalArgumentException("metadata is required");
        }
        return pending(event.aggregateType(), event.aggregateId(), event.eventName(), payload, metadata.correlationId());
    }

    public OutboxEvent markDispatched() {
        return new OutboxEvent(id, aggregateType, aggregateId, eventType, payload,
                OutboxStatus.DISPATCHED, retryCount, occurredAt, null, correlationId, null);
    }

    public OutboxEvent markFailed(String reason, Duration retryDelay) {
        if (retryDelay == null || retryDelay.isNegative()) {
            throw new IllegalArgumentException("retryDelay must be zero or positive");
        }
        return new OutboxEvent(id, aggregateType, aggregateId, eventType, payload,
                OutboxStatus.FAILED, retryCount + 1, occurredAt, Instant.now().plus(retryDelay),
                correlationId, requireText(reason, "reason"));
    }

    public OutboxEvent moveToDeadLetter(String reason) {
        return new OutboxEvent(id, aggregateType, aggregateId, eventType, payload,
                OutboxStatus.DEAD_LETTERED, retryCount, occurredAt, null, correlationId,
                requireText(reason, "reason"));
    }

    public boolean dispatchableAt(Instant instant) {
        if (instant == null) {
            throw new IllegalArgumentException("instant is required");
        }
        return (status == OutboxStatus.PENDING || status == OutboxStatus.FAILED)
                && nextRetryAt != null
                && !nextRetryAt.isAfter(instant);
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return value.trim();
    }
}
