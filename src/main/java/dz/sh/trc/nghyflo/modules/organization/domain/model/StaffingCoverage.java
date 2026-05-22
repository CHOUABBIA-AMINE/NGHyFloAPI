/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : StaffingCoverage
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Staffing coverage across regions, structures, pipelines, and stations.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import dz.sh.trc.nghyflo.shared.domain.value.StationId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;
import java.util.Set;

public record StaffingCoverage(
        Set<RegionId> regionIds,
        Set<StructureId> structureIds,
        Set<PipelineId> pipelineIds,
        Set<StationId> stationIds
) {

    public StaffingCoverage {
        regionIds = regionIds == null ? Set.of() : Set.copyOf(regionIds);
        structureIds = structureIds == null ? Set.of() : Set.copyOf(structureIds);
        pipelineIds = pipelineIds == null ? Set.of() : Set.copyOf(pipelineIds);
        stationIds = stationIds == null ? Set.of() : Set.copyOf(stationIds);
    }

    public boolean includes(StaffingCoverage required) {
        if (required == null) {
            return false;
        }
        return regionIds.containsAll(required.regionIds)
                && structureIds.containsAll(required.structureIds)
                && pipelineIds.containsAll(required.pipelineIds)
                && stationIds.containsAll(required.stationIds);
    }
}
