package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.AssignmentId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import java.time.Instant;

public class CoverageAllocation {
    private final AssignmentId id;
    private final EmployeeId employeeId;
    private final ShiftId shiftId;
    private final StaffingCoverage coverage;
    private final Instant createdAt;
    private AssignmentStatus status;

    public CoverageAllocation(AssignmentId id, EmployeeId employeeId, ShiftId shiftId, StaffingCoverage coverage, Instant createdAt) {
        if (id == null || employeeId == null || shiftId == null || coverage == null || createdAt == null) {
            throw new IllegalArgumentException("coverage allocation fields are required");
        }
        this.id = id;
        this.employeeId = employeeId;
        this.shiftId = shiftId;
        this.coverage = coverage;
        this.createdAt = createdAt;
        this.status = AssignmentStatus.PLANNED;
    }

    public void activate() {
        status = AssignmentStatus.ACTIVE;
    }

    public void complete() {
        status = AssignmentStatus.COMPLETED;
    }

    public void cancel() {
        status = AssignmentStatus.CANCELLED;
    }

    public boolean includes(StaffingCoverage requiredCoverage) {
        return coverage.includes(requiredCoverage);
    }

    public AssignmentId id() {
        return id;
    }

    public EmployeeId employeeId() {
        return employeeId;
    }

    public ShiftId shiftId() {
        return shiftId;
    }

    public StaffingCoverage coverage() {
        return coverage;
    }

    public AssignmentStatus status() {
        return status;
    }

    public Instant createdAt() {
        return createdAt;
    }
}
