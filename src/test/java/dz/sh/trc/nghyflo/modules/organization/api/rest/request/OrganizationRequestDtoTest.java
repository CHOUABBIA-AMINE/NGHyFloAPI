/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationRequestDtoTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.modules.organization.api.rest.request
 *
 * @Description : Verifies organization REST request DTO validation and null collection defaults.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.api.rest.request;

import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrganizationRequestDtoTest {

    @Test
    void shouldCreateCoverageRequestWithEmptyDefaultSets() {
        EvaluateCoverageRequest request = new EvaluateCoverageRequest("employee-id", null, null, null, null);

        assertEquals("employee-id", request.employeeId());
        assertTrue(request.regionIds().isEmpty());
        assertTrue(request.structureIds().isEmpty());
        assertTrue(request.pipelineIds().isEmpty());
        assertTrue(request.stationIds().isEmpty());
    }

    @Test
    void shouldCreateOperationalScopeRequestWithShiftValues() {
        EvaluateOperationalScopeRequest request = new EvaluateOperationalScopeRequest(
                "employee-id",
                Set.of("region-id"),
                Set.of("structure-id"),
                Set.of("pipeline-id"),
                Set.of("station-id"),
                Set.of("shift-id")
        );

        assertEquals("employee-id", request.employeeId());
        assertTrue(request.shiftIds().contains("shift-id"));
    }

    @Test
    void shouldRejectMissingEmployeeId() {
        assertThrows(IllegalArgumentException.class, () -> new EvaluateCoverageRequest(" ", null, null, null, null));
        assertThrows(IllegalArgumentException.class,
                () -> new EvaluateOperationalScopeRequest(" ", null, null, null, null, null));
    }
}
