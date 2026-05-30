/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OperationalScopeCoverageMapper
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Mapper
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.mapper
 *
 * @Description : Converts platform operational scopes into organization staffing coverage requirements.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.application.mapper;

import dz.sh.trc.nghyflo.modules.organization.domain.model.StaffingCoverage;
import dz.sh.trc.nghyflo.platform.security.authorization.OperationalScope;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import dz.sh.trc.nghyflo.shared.domain.value.StationId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.StructureId;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OperationalScopeCoverageMapper {

    public StaffingCoverage toCoverage(OperationalScope scope) {
        if (scope == null) {
            throw new IllegalArgumentException("scope is required");
        }
        return new StaffingCoverage(
                typed(scope.regionIds(), RegionId::of),
                typed(scope.structureIds(), StructureId::of),
                typed(scope.pipelineIds(), PipelineId::of),
                typed(scope.stationIds(), StationId::of)
        );
    }

    private static <T> Set<T> typed(Set<String> values, Function<String, T> mapper) {
        return values.stream()
                .filter(value -> !"*".equals(value))
                .map(mapper)
                .collect(Collectors.toUnmodifiableSet());
    }
}
