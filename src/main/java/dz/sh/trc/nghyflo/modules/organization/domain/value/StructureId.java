/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : StructureId
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : UUID-backed organization structure identifier.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

import java.util.UUID;

public record StructureId(String value) {

    public StructureId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("structureId is required");
        }
        UUID.fromString(value);
    }

    public static StructureId newId() {
        return new StructureId(UUID.randomUUID().toString());
    }

    public static StructureId of(String value) {
        return new StructureId(value);
    }
}
