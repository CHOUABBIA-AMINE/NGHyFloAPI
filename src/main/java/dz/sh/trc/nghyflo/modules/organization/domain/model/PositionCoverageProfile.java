/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : PositionCoverageProfile
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Position coverage and capability profile for operational staffing.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.PositionId;
import java.util.HashSet;
import java.util.Set;

public class PositionCoverageProfile {
    private final PositionId positionId;
    private final StaffingCoverage coverage;
    private final Set<String> capabilityCodes = new HashSet<>();

    public PositionCoverageProfile(PositionId positionId, StaffingCoverage coverage) {
        if (positionId == null) {
            throw new IllegalArgumentException("positionId is required");
        }
        if (coverage == null) {
            throw new IllegalArgumentException("coverage is required");
        }
        this.positionId = positionId;
        this.coverage = coverage;
    }

    public void addCapability(String capabilityCode) {
        if (capabilityCode == null || capabilityCode.isBlank()) {
            throw new IllegalArgumentException("capability code is required");
        }
        capabilityCodes.add(capabilityCode.trim().toUpperCase());
    }

    public boolean hasCapability(String capabilityCode) {
        if (capabilityCode == null || capabilityCode.isBlank()) {
            return false;
        }
        return capabilityCodes.contains(capabilityCode.trim().toUpperCase());
    }

    public boolean covers(StaffingCoverage requiredCoverage) {
        return coverage.includes(requiredCoverage);
    }

    public PositionId positionId() {
        return positionId;
    }

    public StaffingCoverage coverage() {
        return coverage;
    }

    public Set<String> capabilityCodes() {
        return Set.copyOf(capabilityCodes);
    }
}
