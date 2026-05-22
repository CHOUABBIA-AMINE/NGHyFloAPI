/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationStructureModelTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Verifies organization unit, position, coverage profile, shift plan, and staffing requirement behavior.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.AssignmentId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeNumber;
import dz.sh.trc.nghyflo.modules.organization.domain.value.OrganizationUnitCode;
import dz.sh.trc.nghyflo.modules.organization.domain.value.OrganizationUnitId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.PositionCode;
import dz.sh.trc.nghyflo.modules.organization.domain.value.PositionId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.StructureId;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import java.time.Instant;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrganizationStructureModelTest {

    @Test
    void shouldCreateOrganizationUnitAndPosition() {
        OrganizationUnitId unitId = OrganizationUnitId.newId();
        OrganizationUnit parent = new OrganizationUnit(
                OrganizationUnitId.newId(),
                OrganizationUnitCode.of("trc"),
                "TRC"
        );
        OrganizationUnit unit = new OrganizationUnit(unitId, OrganizationUnitCode.of("trc-ops"), "Pipeline Operations");
        unit.attachToParent(parent.id());
        unit.assignStructure(StructureId.newId());
        Position position = new Position(PositionId.newId(), PositionCode.of("supervisor"), "Supervisor", unit.id());

        assertEquals("TRC-OPS", unit.code().value());
        assertTrue(unit.parentId().isPresent());
        assertTrue(unit.structureId().isPresent());
        assertEquals(unitId, position.organizationUnitId());
        assertTrue(position.active());
    }

    @Test
    void shouldTrackEmployeeNumber() {
        Employee employee = new Employee(EmployeeId.newId(), "Operator One", EmployeeNumber.of("e-0001"));

        assertTrue(employee.employeeNumber().isPresent());
        assertEquals("E-0001", employee.employeeNumber().orElseThrow().value());
        employee.assignEmployeeNumber(EmployeeNumber.of("e-0002"));
        assertEquals("E-0002", employee.employeeNumber().orElseThrow().value());
    }

    @Test
    void shouldEvaluatePositionCoverageProfile() {
        RegionId regionId = RegionId.newId();
        PipelineId pipelineId = PipelineId.newId();
        StaffingCoverage coverage = new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(pipelineId), Set.of());
        PositionCoverageProfile profile = new PositionCoverageProfile(PositionId.newId(), coverage);
        profile.addCapability("flow-approval");

        assertTrue(profile.hasCapability("FLOW-APPROVAL"));
        assertTrue(profile.covers(new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(pipelineId), Set.of())));
        assertFalse(profile.hasCapability("incident-escalation"));
    }

    @Test
    void shouldTrackShiftCoveragePlanLifecycle() {
        RegionId regionId = RegionId.newId();
        StaffingCoverage coverage = new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(), Set.of());
        ShiftCoveragePlan plan = new ShiftCoveragePlan(
                AssignmentId.newId(),
                EmployeeId.newId(),
                PositionId.newId(),
                ShiftId.newId(),
                coverage,
                Instant.parse("2026-05-22T08:00:00Z")
        );

        assertEquals(AssignmentStatus.PLANNED, plan.status());
        assertTrue(plan.covers(new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(), Set.of())));
        plan.activate();
        assertEquals(AssignmentStatus.ACTIVE, plan.status());
        plan.cancel();
        assertEquals(AssignmentStatus.CANCELLED, plan.status());
    }

    @Test
    void shouldEvaluateStaffingRequirement() {
        RegionId regionId = RegionId.newId();
        StaffingCoverage required = new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(), Set.of());
        StaffingRequirement requirement = new StaffingRequirement(PositionId.newId(), ShiftId.newId(), required, 2);

        assertTrue(requirement.satisfiedBy(2, required));
        assertFalse(requirement.satisfiedBy(1, required));
        assertFalse(requirement.satisfiedBy(2, new StaffingCoverage(Set.of(RegionId.newId()), Set.of(), Set.of(), Set.of())));
    }
}
