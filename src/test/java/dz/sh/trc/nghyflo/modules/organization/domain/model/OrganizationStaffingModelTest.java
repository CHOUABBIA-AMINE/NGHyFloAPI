/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationStaffingModelTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Verifies organization staffing identifiers, employee state, shift code, and coverage allocation behavior.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.AssignmentId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.CoverageAllocationId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeNumber;
import dz.sh.trc.nghyflo.modules.organization.domain.value.OrganizationUnitCode;
import dz.sh.trc.nghyflo.modules.organization.domain.value.OrganizationUnitId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.PositionCode;
import dz.sh.trc.nghyflo.modules.organization.domain.value.PositionId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftCode;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.StructureId;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import java.time.Instant;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrganizationStaffingModelTest {

    @Test
    void shouldCreateOrganizationSpecificIdentifiersAndCodes() {
        assertNotNull(EmployeeId.newId());
        assertNotNull(StructureId.newId());
        assertNotNull(OrganizationUnitId.newId());
        assertNotNull(PositionId.newId());
        assertNotNull(CoverageAllocationId.newId().asAssignmentId());

        assertEquals("TRC-OPS", OrganizationUnitCode.of(" trc-ops ").value());
        assertEquals("SUPERVISOR", PositionCode.of(" supervisor ").value());
        assertEquals("E-0001", EmployeeNumber.of(" e-0001 ").value());
        assertEquals("DAY", ShiftCode.of(" day ").value());
    }

    @Test
    void shouldRejectInvalidOrganizationIdentifiersAndCodes() {
        assertThrows(IllegalArgumentException.class, () -> EmployeeId.of("invalid"));
        assertThrows(IllegalArgumentException.class, () -> StructureId.of("invalid"));
        assertThrows(IllegalArgumentException.class, () -> OrganizationUnitCode.of(" "));
        assertThrows(IllegalArgumentException.class, () -> PositionCode.of(" "));
        assertThrows(IllegalArgumentException.class, () -> EmployeeNumber.of(" "));
        assertThrows(IllegalArgumentException.class, () -> ShiftCode.of(" "));
    }

    @Test
    void shouldTrackEmployeeOperationalAvailability() {
        Employee employee = new Employee(EmployeeId.newId(), "Operator One");

        assertFalse(employee.activeInStructure());
        employee.assignStructure(StructureId.newId());
        assertTrue(employee.activeInStructure());
        employee.markOnLeave();
        assertFalse(employee.activeInStructure());
    }

    @Test
    void shouldTrackShiftCodeAndTimeWindow() {
        Instant start = Instant.parse("2026-05-22T06:00:00Z");
        Instant end = Instant.parse("2026-05-22T14:00:00Z");
        Shift shift = new Shift(ShiftId.newId(), ShiftCode.of(" morning "), start, end);

        assertEquals("MORNING", shift.code());
        assertEquals("MORNING", shift.shiftCode().value());
        assertTrue(shift.activeAt(start));
        assertFalse(shift.activeAt(end));
    }

    @Test
    void shouldTrackCoverageAllocationLifecycleAndCoverage() {
        RegionId regionId = RegionId.newId();
        PipelineId pipelineId = PipelineId.newId();
        StaffingCoverage coverage = new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(pipelineId), Set.of());
        CoverageAllocation allocation = new CoverageAllocation(
                AssignmentId.newId(),
                EmployeeId.newId(),
                ShiftId.newId(),
                coverage,
                Instant.parse("2026-05-22T08:00:00Z")
        );

        assertEquals(AssignmentStatus.PLANNED, allocation.status());
        assertTrue(allocation.includes(new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(pipelineId), Set.of())));
        allocation.activate();
        assertEquals(AssignmentStatus.ACTIVE, allocation.status());
        allocation.complete();
        assertEquals(AssignmentStatus.COMPLETED, allocation.status());
    }
}
