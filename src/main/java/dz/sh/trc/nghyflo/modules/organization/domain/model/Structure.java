/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Structure
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-30
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Organization-owned structure within a region.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.StructureId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;

public record Structure(StructureId id, RegionId regionId, String name) {
    public Structure {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Structure name is required");
        }
    }
}
