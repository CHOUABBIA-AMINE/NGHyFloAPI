/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OperationalScope
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authorization
 *
 * @Description : Represents operational authorization scope across region, structure, pipeline, station, and shift.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authorization;

import java.util.Set;

public record OperationalScope(
        Set<String> regionIds,
        Set<String> structureIds,
        Set<String> pipelineIds,
        Set<String> stationIds,
        Set<String> shiftIds
) {

    public OperationalScope {
        regionIds = safe(regionIds);
        structureIds = safe(structureIds);
        pipelineIds = safe(pipelineIds);
        stationIds = safe(stationIds);
        shiftIds = safe(shiftIds);
    }

    public static OperationalScope unrestricted() {
        return new OperationalScope(Set.of("*"), Set.of("*"), Set.of("*"), Set.of("*"), Set.of("*"));
    }

    public boolean covers(OperationalScope required) {
        if (required == null) {
            return false;
        }
        return covers(regionIds, required.regionIds)
                && covers(structureIds, required.structureIds)
                && covers(pipelineIds, required.pipelineIds)
                && covers(stationIds, required.stationIds)
                && covers(shiftIds, required.shiftIds);
    }

    private static Set<String> safe(Set<String> values) {
        return values == null ? Set.of() : Set.copyOf(values);
    }

    private static boolean covers(Set<String> granted, Set<String> required) {
        if (required.isEmpty()) {
            return true;
        }
        return granted.contains("*") || granted.containsAll(required);
    }
}
