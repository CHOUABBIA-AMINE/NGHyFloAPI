/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CoverageAllocationId
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : UUID-backed coverage allocation identifier.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

import java.util.UUID;

public record CoverageAllocationId(String value) {

    public CoverageAllocationId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("coverageAllocationId is required");
        }
        UUID.fromString(value);
    }

    public static CoverageAllocationId newId() {
        return new CoverageAllocationId(UUID.randomUUID().toString());
    }

    public static CoverageAllocationId of(String value) {
        return new CoverageAllocationId(value);
    }

    public AssignmentId asAssignmentId() {
        return AssignmentId.of(value);
    }
}
