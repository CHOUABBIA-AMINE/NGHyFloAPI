/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SharedKernelIdentifierTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared
 *
 * @Description : Verifies UUID validation and factory behavior for all shared NGHyFlo identifiers.
 *
 */
package dz.sh.trc.nghyflo.shared;

import dz.sh.trc.nghyflo.shared.domain.value.CausationId;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.EventId;
import dz.sh.trc.nghyflo.shared.domain.value.GatewayId;
import dz.sh.trc.nghyflo.shared.domain.value.MeasurementPointId;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import dz.sh.trc.nghyflo.shared.domain.value.SegmentId;
import dz.sh.trc.nghyflo.shared.domain.value.SensorId;
import dz.sh.trc.nghyflo.shared.domain.value.StationId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import dz.sh.trc.nghyflo.shared.domain.value.WorkflowInstanceId;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SharedKernelIdentifierTest {

    @Test
    void shouldCreateIdentifiersFromGeneratedUuidFactories() {
        assertDoesNotThrow(TenantId::newId);
        assertDoesNotThrow(RegionId::newId);
        assertDoesNotThrow(StructureId::newId);
        assertDoesNotThrow(PipelineId::newId);
        assertDoesNotThrow(SegmentId::newId);
        assertDoesNotThrow(StationId::newId);
        assertDoesNotThrow(MeasurementPointId::newId);
        assertDoesNotThrow(SensorId::newId);
        assertDoesNotThrow(GatewayId::newId);
        assertDoesNotThrow(UserId::newId);
        assertDoesNotThrow(EmployeeId::newId);
        assertDoesNotThrow(WorkflowInstanceId::newId);
        assertDoesNotThrow(EventId::newId);
        assertDoesNotThrow(CorrelationId::newId);
        assertDoesNotThrow(CausationId::newId);
    }

    @Test
    void shouldCreateIdentifierFromExplicitUuidValue() {
        String value = UUID.randomUUID().toString();

        PipelineId pipelineId = PipelineId.of(value);

        assertEquals(value, pipelineId.value());
    }

    @Test
    void shouldPreserveCanonicalConstructorCompatibility() {
        String value = UUID.randomUUID().toString();

        UserId userId = new UserId(value);

        assertEquals(value, userId.value());
    }

    @Test
    void shouldRejectBlankIdentifierValue() {
        assertThrows(IllegalArgumentException.class, () -> PipelineId.of(" "));
        assertThrows(IllegalArgumentException.class, () -> UserId.of(" "));
        assertThrows(IllegalArgumentException.class, () -> CorrelationId.of(" "));
    }

    @Test
    void shouldRejectNonUuidIdentifierValue() {
        assertThrows(IllegalArgumentException.class, () -> PipelineId.of("pipeline-1"));
        assertThrows(IllegalArgumentException.class, () -> TenantId.of("tenant-main"));
        assertThrows(IllegalArgumentException.class, () -> EventId.of("event-1"));
    }
}
