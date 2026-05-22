/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OutboxEventTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.events.outbox
 *
 * @Description : Verifies transactional outbox event validation and status lifecycle behavior.
 *
 */
package dz.sh.trc.nghyflo.platform.events.outbox;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OutboxEventTest {

    @Test
    void shouldCreatePendingOutboxEvent() {
        OutboxEvent event = OutboxEvent.pending(
                "FlowReading",
                "reading-1",
                "nghyflo.measurement.submitted.v1",
                "{\"id\":\"reading-1\"}",
                CorrelationId.newId()
        );

        assertNotNull(event.id());
        assertEquals(OutboxStatus.PENDING, event.status());
        assertEquals(0, event.retryCount());
        assertNotNull(event.nextRetryAt());
        assertTrue(event.dispatchableAt(Instant.now().plusSeconds(1)));
    }

    @Test
    void shouldMarkEventAsDispatched() {
        OutboxEvent dispatched = OutboxEvent.pending(
                "WorkflowInstance",
                "workflow-1",
                "nghyflo.workflow.transitioned.v1",
                "{\"state\":\"APPROVED\"}",
                CorrelationId.newId()
        ).markDispatched();

        assertEquals(OutboxStatus.DISPATCHED, dispatched.status());
        assertFalse(dispatched.dispatchableAt(Instant.now().plusSeconds(1)));
    }

    @Test
    void shouldMarkEventAsFailedAndScheduleRetry() {
        OutboxEvent failed = OutboxEvent.pending(
                "TelemetryFrame",
                "frame-1",
                "nghyflo.telemetry.raw.v1",
                "{\"quality\":\"GOOD\"}",
                CorrelationId.newId()
        ).markFailed("temporary transport failure", Duration.ofSeconds(30));

        assertEquals(OutboxStatus.FAILED, failed.status());
        assertEquals(1, failed.retryCount());
        assertEquals("temporary transport failure", failed.errorReason());
        assertFalse(failed.dispatchableAt(Instant.now()));
    }

    @Test
    void shouldMoveEventToDeadLetter() {
        OutboxEvent deadLettered = OutboxEvent.pending(
                "Incident",
                "incident-1",
                "nghyflo.incident.opened.v1",
                "{\"severity\":\"HIGH\"}",
                CorrelationId.newId()
        ).moveToDeadLetter("retry exhausted");

        assertEquals(OutboxStatus.DEAD_LETTERED, deadLettered.status());
        assertEquals("retry exhausted", deadLettered.errorReason());
        assertFalse(deadLettered.dispatchableAt(Instant.now().plusSeconds(1)));
    }

    @Test
    void shouldRejectInvalidRequiredFields() {
        assertThrows(IllegalArgumentException.class, () -> OutboxEvent.pending(
                " ",
                "id",
                "nghyflo.test.v1",
                "{}",
                CorrelationId.newId()
        ));
        assertThrows(IllegalArgumentException.class, () -> OutboxEvent.pending(
                "Aggregate",
                "id",
                "nghyflo.test.v1",
                " ",
                CorrelationId.newId()
        ));
        assertThrows(IllegalArgumentException.class, () -> OutboxEvent.pending(
                "Aggregate",
                "id",
                "nghyflo.test.v1",
                "{}",
                null
        ));
    }
}
