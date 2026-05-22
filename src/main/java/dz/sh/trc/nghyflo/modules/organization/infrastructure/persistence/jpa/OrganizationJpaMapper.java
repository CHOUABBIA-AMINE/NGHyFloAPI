/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationJpaMapper
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Mapper
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa
 *
 * @Description : Maps organization JPA projections to organization domain models.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import dz.sh.trc.nghyflo.modules.organization.domain.model.AssignmentStatus;
import dz.sh.trc.nghyflo.modules.organization.domain.model.CoverageAllocation;
import dz.sh.trc.nghyflo.modules.organization.domain.model.Employee;
import dz.sh.trc.nghyflo.modules.organization.domain.model.EmployeeStatus;
import dz.sh.trc.nghyflo.modules.organization.domain.model.StaffingCoverage;
import dz.sh.trc.nghyflo.modules.organization.domain.value.AssignmentId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import dz.sh.trc.nghyflo.shared.domain.value.StationId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class OrganizationJpaMapper {

    public Employee toEmployee(EmployeeJpaEntity entity) {
        Employee employee = new Employee(EmployeeId.of(entity.id()), entity.fullName());
        if (entity.structureId() != null && !entity.structureId().isBlank()) {
            employee.assignStructure(StructureId.of(entity.structureId()));
        }
        applyEmployeeStatus(employee, entity.status());
        return employee;
    }

    public CoverageAllocation toAllocation(CoverageAllocationJpaEntity entity) {
        CoverageAllocation allocation = new CoverageAllocation(
                AssignmentId.of(entity.id()),
                EmployeeId.of(entity.employeeId()),
                ShiftId.of(entity.shiftId()),
                new StaffingCoverage(
                        typed(entity.regionIds(), RegionId::of),
                        typed(entity.structureIds(), StructureId::of),
                        typed(entity.pipelineIds(), PipelineId::of),
                        typed(entity.stationIds(), StationId::of)
                ),
                entity.createdAt()
        );
        applyAllocationStatus(allocation, entity.status());
        return allocation;
    }

    private void applyEmployeeStatus(Employee employee, String status) {
        if (EmployeeStatus.SUSPENDED.name().equals(status)) {
            employee.suspend();
        } else if (EmployeeStatus.ON_LEAVE.name().equals(status)) {
            employee.markOnLeave();
        } else if (EmployeeStatus.RETIRED.name().equals(status)) {
            employee.retire();
        }
    }

    private void applyAllocationStatus(CoverageAllocation allocation, String status) {
        if (AssignmentStatus.ACTIVE.name().equals(status)) {
            allocation.activate();
        } else if (AssignmentStatus.COMPLETED.name().equals(status)) {
            allocation.complete();
        } else if (AssignmentStatus.CANCELLED.name().equals(status)) {
            allocation.cancel();
        }
    }

    private interface ValueFactory<T> {
        T create(String value);
    }

    private static <T> Set<T> typed(String csv, ValueFactory<T> factory) {
        if (csv == null || csv.isBlank()) {
            return Set.of();
        }
        return Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .map(factory::create)
                .collect(Collectors.toUnmodifiableSet());
    }
}
