/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationApiMapper
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Mapper
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.modules.organization.api.rest.mapper
 *
 * @Description : Maps organization REST requests into application commands.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.api.rest.mapper;

import dz.sh.trc.nghyflo.modules.organization.api.rest.request.EvaluateCoverageRequest;
import dz.sh.trc.nghyflo.modules.organization.api.rest.request.EvaluateOperationalScopeRequest;
import dz.sh.trc.nghyflo.modules.organization.application.command.EvaluateCoverageCommand;
import dz.sh.trc.nghyflo.modules.organization.application.command.EvaluateOperationalScopeCommand;
import dz.sh.trc.nghyflo.modules.organization.domain.model.StaffingCoverage;
import dz.sh.trc.nghyflo.platform.security.authorization.OperationalScope;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.PipelineId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import dz.sh.trc.nghyflo.shared.domain.value.StationId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrganizationApiMapper {

    public EvaluateCoverageCommand toCommand(EvaluateCoverageRequest request) {
        return new EvaluateCoverageCommand(
                EmployeeId.of(request.employeeId()),
                new StaffingCoverage(
                        typed(request.regionIds(), RegionId::of),
                        typed(request.structureIds(), StructureId::of),
                        typed(request.pipelineIds(), PipelineId::of),
                        typed(request.stationIds(), StationId::of)
                )
        );
    }

    public EvaluateOperationalScopeCommand toCommand(EvaluateOperationalScopeRequest request) {
        return new EvaluateOperationalScopeCommand(
                EmployeeId.of(request.employeeId()),
                new OperationalScope(
                        request.regionIds(),
                        request.structureIds(),
                        request.pipelineIds(),
                        request.stationIds(),
                        request.shiftIds()
                )
        );
    }

    private static <T> Set<T> typed(Set<String> values, Function<String, T> mapper) {
        return values.stream().map(mapper).collect(Collectors.toUnmodifiableSet());
    }
}
