/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationDomainModelTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Verifies organization domain model behavior.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.StructureId;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import java.time.Instant;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrganizationDomainModelTest {

    @Test
    void shouldManageEmployeeStatusAndStructure() {
        Employee employee = new Employee(EmployeeId.newId(), "Operator One");

        assertEquals(EmployeeStatus.ACTIVE, employee.status());
        assertFalse(employee.activeInStructure());
        employee.assignStructure(StructureId.newId());
        assertTrue(employee.activeInStructure());
        employee.suspend();
        assertEquals(EmployeeStatus.SUSPENDED, employee.status());
        assertFalse(employee.activeInStructure());
    }

    @Test
    void shouldValidateShiftWindow() {
        Instant start = Instant.parse("2026-05-22T08:00:00Z");
        Instant end = Instant.parse("2026-05-22T16:00:00Z");
        Shift shift = new Shift(ShiftId.newId(), "day", start, end);

        assertEquals("DAY", shift.code());
        assertTrue(shift.activeAt(Instant.parse("2026-05-22T10:00:00Z")));
        assertFalse(shift.activeAt(end));
        assertThrows(IllegalArgumentException.class, () -> new Shift(ShiftId.newId(), "bad", end, start));
    }

    @Test
    void shouldCompareCoverageInclusion() {
        RegionId regionId = RegionId.newId();
        StructureId structureId = StructureId.newId();
        PipelineId pipelineId = PipelineId.newId();
        StaffingCoverage granted = new StaffingCoverage(Set.of(regionId), Set.of(structureId), Set.of(pipelineId), Set.of());
        StaffingCoverage required = new StaffingCoverage(Set.of(regionId), Set.of(), Set.of(pipelineId), Set.of());
        StaffingCoverage denied = new StaffingCoverage(Set.of(RegionId.newId()), Set.of(), Set.of(), Set.of());

        assertTrue(granted.includes(required));
        assertFalse(granted.includes(denied));
    }
}
