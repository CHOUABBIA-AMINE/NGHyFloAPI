/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationCoverageServiceTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.service
 *
 * @Description : Verifies organization coverage evaluation service decisions.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.application.service;

import dz.sh.trc.nghyflo.modules.organization.application.command.EvaluateCoverageCommand;
import dz.sh.trc.nghyflo.modules.organization.application.dto.CoverageEvaluationResponse;
import dz.sh.trc.nghyflo.modules.organization.application.port.CoverageAllocationPort;
import dz.sh.trc.nghyflo.modules.organization.application.port.EmployeeLookupPort;
import dz.sh.trc.nghyflo.modules.organization.domain.model.CoverageAllocation;
import dz.sh.trc.nghyflo.modules.organization.domain.model.Employee;
import dz.sh.trc.nghyflo.modules.organization.domain.model.StaffingCoverage;
import dz.sh.trc.nghyflo.modules.organization.domain.value.AssignmentId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrganizationCoverageServiceTest {

    @Test
    void shouldGrantWhenEmployeeHasActiveCoverage() {
        EmployeeId employeeId = EmployeeId.newId();
        RegionId regionId = RegionId.newId();
        PipelineId pipelineId = PipelineId.newId();
        Employee employee = employee(employeeId);
        StaffingCoverage granted = new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(pipelineId), Set.of());
        CoverageAllocation allocation = allocation(employeeId, granted);
        allocation.activate();
        OrganizationCoverageService service = service(employee, List.of(allocation));

        CoverageEvaluationResponse response = service.evaluate(new EvaluateCoverageCommand(
                employeeId,
                new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(pipelineId), Set.of())
        ));

        assertTrue(response.allowed());
        assertEquals("COVERAGE_GRANTED", response.reasonCode());
    }

    @Test
    void shouldDenyWhenEmployeeHasNoActiveCoverage() {
        EmployeeId employeeId = EmployeeId.newId();
        Employee employee = employee(employeeId);
        OrganizationCoverageService service = service(employee, List.of());

        CoverageEvaluationResponse response = service.evaluate(new EvaluateCoverageCommand(
                employeeId,
                new StaffingCoverage(Set.of(RegionId.newId()), Set.of(), Set.of(), Set.of())
        ));

        assertFalse(response.allowed());
        assertEquals("NO_ACTIVE_COVERAGE", response.reasonCode());
    }

    @Test
    void shouldDenyWhenEmployeeCoverageDoesNotIncludeRequest() {
        EmployeeId employeeId = EmployeeId.newId();
        Employee employee = employee(employeeId);
        CoverageAllocation allocation = allocation(employeeId,
                new StaffingCoverage(Set.of(RegionId.newId()), Set.of(), Set.of(), Set.of()));
        allocation.activate();
        OrganizationCoverageService service = service(employee, List.of(allocation));

        CoverageEvaluationResponse response = service.evaluate(new EvaluateCoverageCommand(
                employeeId,
                new StaffingCoverage(Set.of(RegionId.newId()), Set.of(), Set.of(), Set.of())
        ));

        assertFalse(response.allowed());
        assertEquals("COVERAGE_DENIED", response.reasonCode());
    }

    private Employee employee(EmployeeId employeeId) {
        Employee employee = new Employee(employeeId, "Operator One");
        employee.assignStructure(StructureId.newId());
        return employee;
    }

    private CoverageAllocation allocation(EmployeeId employeeId, StaffingCoverage coverage) {
        return new CoverageAllocation(AssignmentId.newId(), employeeId, ShiftId.newId(), coverage, Instant.now());
    }

    private OrganizationCoverageService service(Employee employee, List<CoverageAllocation> allocations) {
        EmployeeLookupPort employeeLookup = id -> Optional.ofNullable(employee).filter(value -> value.id().equals(id));
        CoverageAllocationPort allocationLookup = id -> allocations.stream()
                .filter(value -> value.employeeId().equals(id))
                .toList();
        return new OrganizationCoverageService(employeeLookup, allocationLookup);
    }
}
