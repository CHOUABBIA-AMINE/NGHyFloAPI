package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;

public record Structure(StructureId id, RegionId regionId, String name) {
    public Structure {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Structure name is required");
    }
}
