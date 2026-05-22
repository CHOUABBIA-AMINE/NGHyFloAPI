/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EvaluateCoverageRequest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.modules.organization.api.rest.request
 *
 * @Description : REST request payload for evaluating organization coverage.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.api.rest.request;

import java.util.Set;

public record EvaluateCoverageRequest(
        String employeeId,
        Set<String> regionIds,
        Set<String> structureIds,
        Set<String> pipelineIds,
        Set<String> stationIds
) {

    public EvaluateCoverageRequest {
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException("employeeId is required");
        }
        regionIds = safe(regionIds);
        structureIds = safe(structureIds);
        pipelineIds = safe(pipelineIds);
        stationIds = safe(stationIds);
    }

    private static Set<String> safe(Set<String> values) {
        return values == null ? Set.of() : Set.copyOf(values);
    }
}
