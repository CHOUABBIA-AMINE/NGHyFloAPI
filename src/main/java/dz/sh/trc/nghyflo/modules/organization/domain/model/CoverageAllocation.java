/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CoverageAllocation
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Allocates an employee to an operational shift and staffing coverage area.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.AssignmentId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;
import java.time.Instant;

public class CoverageAllocation {
    private final AssignmentId id;
    private final EmployeeId employeeId;
    private final ShiftId shiftId;
    private final StaffingCoverage coverage;
    private final Instant createdAt;
    private AssignmentStatus status;

    public CoverageAllocation(
            AssignmentId id,
            EmployeeId employeeId,
            ShiftId shiftId,
            StaffingCoverage coverage,
            Instant createdAt
    ) {
        if (id == null) {
            throw new IllegalArgumentException("assignment id is required");
        }
        if (employeeId == null) {
            throw new IllegalArgumentException("employee id is required");
        }
        if (shiftId == null) {
            throw new IllegalArgumentException("shift id is required");
        }
        if (coverage == null) {
            throw new IllegalArgumentException("coverage is required");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt is required");
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
