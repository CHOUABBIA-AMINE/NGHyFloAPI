/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : StaffingRequirement
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Required position capacity and coverage for an operational staffing window.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.PositionId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;

public class StaffingRequirement {
    private final PositionId positionId;
    private final ShiftId shiftId;
    private final StaffingCoverage requiredCoverage;
    private final int requiredCount;

    public StaffingRequirement(
            PositionId positionId,
            ShiftId shiftId,
            StaffingCoverage requiredCoverage,
            int requiredCount
    ) {
        if (positionId == null) {
            throw new IllegalArgumentException("positionId is required");
        }
        if (shiftId == null) {
            throw new IllegalArgumentException("shiftId is required");
        }
        if (requiredCoverage == null) {
            throw new IllegalArgumentException("requiredCoverage is required");
        }
        if (requiredCount <= 0) {
            throw new IllegalArgumentException("requiredCount must be positive");
        }
        this.positionId = positionId;
        this.shiftId = shiftId;
        this.requiredCoverage = requiredCoverage;
        this.requiredCount = requiredCount;
    }

    public boolean satisfiedBy(int availableCount, StaffingCoverage availableCoverage) {
        return availableCount >= requiredCount && availableCoverage != null && availableCoverage.includes(requiredCoverage);
    }

    public PositionId positionId() {
        return positionId;
    }

    public ShiftId shiftId() {
        return shiftId;
    }

    public StaffingCoverage requiredCoverage() {
        return requiredCoverage;
    }

    public int requiredCount() {
        return requiredCount;
    }
}
