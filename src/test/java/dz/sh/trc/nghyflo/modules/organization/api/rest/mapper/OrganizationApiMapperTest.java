/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationApiMapperTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.modules.organization.api.rest.mapper
 *
 * @Description : Verifies mapping from organization REST requests to application commands.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.api.rest.mapper;

import dz.sh.trc.nghyflo.modules.organization.api.rest.request.EvaluateCoverageRequest;
import dz.sh.trc.nghyflo.modules.organization.api.rest.request.EvaluateOperationalScopeRequest;
import dz.sh.trc.nghyflo.modules.organization.application.command.EvaluateCoverageCommand;
import dz.sh.trc.nghyflo.modules.organization.application.command.EvaluateOperationalScopeCommand;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import dz.sh.trc.nghyflo.shared.domain.value.StationId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrganizationApiMapperTest {

    private final OrganizationApiMapper mapper = new OrganizationApiMapper();

    @Test
    void shouldMapCoverageRequestToCommand() {
        EmployeeId employeeId = EmployeeId.newId();
        RegionId regionId = RegionId.newId();
        StructureId structureId = StructureId.newId();
        PipelineId pipelineId = PipelineId.newId();
        StationId stationId = StationId.newId();
        EvaluateCoverageRequest request = new EvaluateCoverageRequest(
                employeeId.value(),
                Set.of(regionId.value()),
                Set.of(structureId.value()),
                Set.of(pipelineId.value()),
                Set.of(stationId.value())
        );

        EvaluateCoverageCommand command = mapper.toCommand(request);

        assertEquals(employeeId, command.employeeId());
        assertTrue(command.requiredCoverage().regionIds().contains(regionId));
        assertTrue(command.requiredCoverage().structureIds().contains(structureId));
        assertTrue(command.requiredCoverage().pipelineIds().contains(pipelineId));
        assertTrue(command.requiredCoverage().stationIds().contains(stationId));
    }

    @Test
    void shouldMapOperationalScopeRequestToCommand() {
        EmployeeId employeeId = EmployeeId.newId();
        RegionId regionId = RegionId.newId();
        EvaluateOperationalScopeRequest request = new EvaluateOperationalScopeRequest(
                employeeId.value(),
                Set.of(regionId.value()),
                Set.of(),
                Set.of(),
                Set.of(),
                Set.of("shift-1")
        );

        EvaluateOperationalScopeCommand command = mapper.toCommand(request);

        assertEquals(employeeId, command.employeeId());
        assertTrue(command.requiredScope().regionIds().contains(regionId.value()));
        assertTrue(command.requiredScope().shiftIds().contains("shift-1"));
    }
}
