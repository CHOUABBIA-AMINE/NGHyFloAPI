/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : DomainEventContractTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared
 *
 * @Description : Verifies NGHyFlo domain event envelope and metadata contract behavior.
 *
 */
package dz.sh.trc.nghyflo.shared;

import dz.sh.trc.nghyflo.shared.domain.event.EventClassification;
import dz.sh.trc.nghyflo.shared.domain.event.EventEnvelope;
import dz.sh.trc.nghyflo.shared.domain.event.EventMetadata;
import dz.sh.trc.nghyflo.shared.domain.event.EventType;
import dz.sh.trc.nghyflo.shared.domain.event.WorkflowDomainEvent;
import dz.sh.trc.nghyflo.shared.domain.value.CausationId;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.EventId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DomainEventContractTest {

    @Test
    void shouldWrapDomainEventWithMetadata() {
        WorkflowStartedForTest event = WorkflowStartedForTest.create();
        EventMetadata metadata = EventMetadata.of(
                UserId.newId(),
                TenantId.newId(),
                event.correlationId(),
                event.causationId(),
                "workflow"
        );

        EventEnvelope<WorkflowStartedForTest> envelope = EventEnvelope.wrap(
                event,
                EventClassification.OPERATIONAL,
                metadata
        );

        assertEquals(event.eventId(), envelope.eventId());
        assertEquals("nghyflo.workflow.started.v1", envelope.eventName());
        assertEquals("WorkflowInstance", envelope.aggregateType());
        assertEquals(EventType.WORKFLOW, envelope.eventType());
        assertEquals(1, envelope.schemaVersion());
        assertEquals(metadata, envelope.metadata());
    }

    @Test
    void shouldRejectMetadataWithoutActor() {
        assertThrows(IllegalArgumentException.class, () -> new EventMetadata(
                null,
                TenantId.newId(),
                CorrelationId.newId(),
                CausationId.newId(),
                "workflow",
                1
        ));
    }

    @Test
    void shouldRejectEnvelopeWithoutPayload() {
        assertThrows(IllegalArgumentException.class, () -> new EventEnvelope<>(
                EventId.newId(),
                "nghyflo.workflow.started.v1",
                "WorkflowInstance",
                CausationId.newId().value(),
                EventType.WORKFLOW,
                EventClassification.OPERATIONAL,
                1,
                Instant.now(),
                EventMetadata.of(UserId.newId(), TenantId.newId(), CorrelationId.newId(), CausationId.newId(), "workflow"),
                null
        ));
    }

    private record WorkflowStartedForTest(
            EventId eventId,
            Instant occurredAt,
            String aggregateId,
            CorrelationId correlationId,
            CausationId causationId
    ) implements WorkflowDomainEvent {

        static WorkflowStartedForTest create() {
            return new WorkflowStartedForTest(
                    EventId.newId(),
                    Instant.now(),
                    CausationId.newId().value(),
                    CorrelationId.newId(),
                    CausationId.newId()
            );
        }

        @Override
        public String aggregateType() {
            return "WorkflowInstance";
        }

        @Override
        public String eventName() {
            return "nghyflo.workflow.started.v1";
        }
    }
}
