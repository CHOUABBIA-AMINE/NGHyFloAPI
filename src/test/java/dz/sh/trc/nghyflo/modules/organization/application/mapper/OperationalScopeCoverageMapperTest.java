/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OperationalScopeCoverageMapperTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.mapper
 *
 * @Description : Verifies platform operational scope to organization coverage conversion.
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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationalScopeCoverageMapperTest {

    @Test
    void shouldMapPlatformScopeToStaffingCoverage() {
        RegionId regionId = RegionId.newId();
        StructureId structureId = StructureId.newId();
        PipelineId pipelineId = PipelineId.newId();
        StationId stationId = StationId.newId();
        OperationalScope scope = new OperationalScope(
                Set.of(regionId.value()),
                Set.of(structureId.value()),
                Set.of(pipelineId.value()),
                Set.of(stationId.value()),
                Set.of("shift-ignored")
        );

        StaffingCoverage coverage = new OperationalScopeCoverageMapper().toCoverage(scope);

        assertTrue(coverage.regionIds().contains(regionId));
        assertTrue(coverage.structureIds().contains(structureId));
        assertTrue(coverage.pipelineIds().contains(pipelineId));
        assertTrue(coverage.stationIds().contains(stationId));
    }

    @Test
    void shouldIgnoreWildcardValues() {
        OperationalScope scope = new OperationalScope(Set.of("*"), Set.of("*"), Set.of("*"), Set.of("*"), Set.of("*"));

        StaffingCoverage coverage = new OperationalScopeCoverageMapper().toCoverage(scope);

        assertEquals(0, coverage.regionIds().size());
        assertEquals(0, coverage.structureIds().size());
        assertEquals(0, coverage.pipelineIds().size());
        assertEquals(0, coverage.stationIds().size());
    }
}
