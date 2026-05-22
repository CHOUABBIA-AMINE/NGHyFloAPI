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
        if (EmployeeStatus.SUSPENDED.name().equals(entity.status())) {
            employee.suspend();
        } else if (EmployeeStatus.ON_LEAVE.name().equals(entity.status())) {
            employee.markOnLeave();
        } else if (EmployeeStatus.RETIRED.name().equals(entity.status())) {
            employee.retire();
        }
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
        if (AssignmentStatus.ACTIVE.name().equals(entity.status())) {
            allocation.activate();
        } else if (AssignmentStatus.COMPLETED.name().equals(entity.status())) {
            allocation.complete();
        } else if (AssignmentStatus.CANCELLED.name().equals(entity.status())) {
            allocation.cancel();
        }
        return allocation;
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
