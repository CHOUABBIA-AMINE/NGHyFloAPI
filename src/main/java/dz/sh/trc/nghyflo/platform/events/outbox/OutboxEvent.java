package dz.sh.trc.nghyflo.platform.events.outbox;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import java.time.Instant;

public record OutboxEvent(String id, String aggregateType, String aggregateId, String eventType, String payload,
                          String status, int retryCount, Instant occurredAt, Instant nextRetryAt,
                          CorrelationId correlationId) {}
