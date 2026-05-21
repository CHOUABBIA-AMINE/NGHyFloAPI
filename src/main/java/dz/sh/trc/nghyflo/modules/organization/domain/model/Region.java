package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.shared.domain.value.RegionId;

public record Region(RegionId id, String name) {
    public Region {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Region name is required");
    }
}
