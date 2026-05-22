/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ShiftCoveragePlan
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Links an employee, position, shift, and coverage area for operational staffing.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.AssignmentId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.PositionId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;
import java.time.Instant;

public class ShiftCoveragePlan {
    private final AssignmentId id;
    private final EmployeeId employeeId;
    private final PositionId positionId;
    private final ShiftId shiftId;
    private final StaffingCoverage coverage;
    private final Instant createdAt;
    private AssignmentStatus status;

    public ShiftCoveragePlan(
            AssignmentId id,
            EmployeeId employeeId,
            PositionId positionId,
            ShiftId shiftId,
            StaffingCoverage coverage,
            Instant createdAt
    ) {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (employeeId == null) {
            throw new IllegalArgumentException("employeeId is required");
        }
        if (positionId == null) {
            throw new IllegalArgumentException("positionId is required");
        }
        if (shiftId == null) {
            throw new IllegalArgumentException("shiftId is required");
        }
        if (coverage == null) {
            throw new IllegalArgumentException("coverage is required");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt is required");
        }
        this.id = id;
        this.employeeId = employeeId;
        this.positionId = positionId;
        this.shiftId = shiftId;
        this.coverage = coverage;
        this.createdAt = createdAt;
        this.status = AssignmentStatus.PLANNED;
    }

    public void activate() {
        this.status = AssignmentStatus.ACTIVE;
    }

    public void complete() {
        this.status = AssignmentStatus.COMPLETED;
    }

    public void cancel() {
        this.status = AssignmentStatus.CANCELLED;
    }

    public boolean covers(StaffingCoverage requiredCoverage) {
        return coverage.includes(requiredCoverage);
    }

    public AssignmentId id() {
        return id;
    }

    public EmployeeId employeeId() {
        return employeeId;
    }

    public PositionId positionId() {
        return positionId;
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
