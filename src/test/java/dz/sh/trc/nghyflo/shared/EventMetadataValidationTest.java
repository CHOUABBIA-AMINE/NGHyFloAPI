/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EventMetadataValidationTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared
 *
 * @Description : Verifies event metadata validation for audit, outbox, and replay readiness.
 *
 */
package dz.sh.trc.nghyflo.shared;

import dz.sh.trc.nghyflo.shared.domain.event.EventMetadata;
import dz.sh.trc.nghyflo.shared.domain.value.CausationId;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventMetadataValidationTest {

    @Test
    void shouldCreateDefaultSchemaVersionMetadata() {
        UserId actorId = UserId.newId();
        TenantId tenantId = TenantId.newId();
        CorrelationId correlationId = CorrelationId.newId();
        CausationId causationId = CausationId.newId();

        EventMetadata metadata = EventMetadata.of(actorId, tenantId, correlationId, causationId, "measurement");

        assertEquals(actorId, metadata.actorId());
        assertEquals(tenantId, metadata.tenantId());
        assertEquals(correlationId, metadata.correlationId());
        assertEquals(causationId, metadata.causationId());
        assertEquals("measurement", metadata.sourceModule());
        assertEquals(1, metadata.schemaVersion());
    }

    @Test
    void shouldRejectInvalidMetadataFields() {
        UserId actorId = UserId.newId();
        TenantId tenantId = TenantId.newId();
        CorrelationId correlationId = CorrelationId.newId();
        CausationId causationId = CausationId.newId();

        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(null, tenantId, correlationId, causationId, "workflow", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(actorId, null, correlationId, causationId, "workflow", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(actorId, tenantId, null, causationId, "workflow", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(actorId, tenantId, correlationId, null, "workflow", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(actorId, tenantId, correlationId, causationId, " ", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(actorId, tenantId, correlationId, causationId, "workflow", 0));
        assertThrows(IllegalArgumentException.class,
                () -> new EventMetadata(actorId, tenantId, correlationId, causationId, "workflow", 1, null));
    }

    @Test
    void shouldPreserveExplicitRecordedAtTimestamp() {
        Instant recordedAt = Instant.parse("2026-05-22T10:00:00Z");

        EventMetadata metadata = new EventMetadata(
                UserId.newId(),
                TenantId.newId(),
                CorrelationId.newId(),
                CausationId.newId(),
                "telemetry",
                2,
                recordedAt
        );

        assertEquals(2, metadata.schemaVersion());
        assertEquals(recordedAt, metadata.recordedAt());
    }
}
