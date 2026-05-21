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
 * @Description : Verifies UUID validation and factory behavior for shared NGHyFlo identifiers.
 *
 */
package dz.sh.trc.nghyflo.shared;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.EventId;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import dz.sh.trc.nghyflo.shared.domain.value.SensorId;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SharedKernelIdentifierTest {

    @Test
    void shouldCreateIdentifiersFromGeneratedUuidFactories() {
        assertDoesNotThrow(PipelineId::newId);
        assertDoesNotThrow(RegionId::newId);
        assertDoesNotThrow(EmployeeId::newId);
        assertDoesNotThrow(SensorId::newId);
        assertDoesNotThrow(EventId::newId);
        assertDoesNotThrow(CorrelationId::newId);
    }

    @Test
    void shouldCreateIdentifierFromExplicitUuidValue() {
        String value = UUID.randomUUID().toString();

        PipelineId pipelineId = PipelineId.of(value);

        assertEquals(value, pipelineId.value());
    }

    @Test
    void shouldRejectBlankIdentifierValue() {
        assertThrows(IllegalArgumentException.class, () -> PipelineId.of(" "));
    }

    @Test
    void shouldRejectNonUuidIdentifierValue() {
        assertThrows(IllegalArgumentException.class, () -> PipelineId.of("pipeline-1"));
    }
}
