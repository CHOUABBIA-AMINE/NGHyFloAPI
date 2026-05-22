/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : PositionId
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : UUID-backed operational position identifier.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

import java.util.UUID;

public record PositionId(String value) {

    public PositionId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("positionId is required");
        }
        UUID.fromString(value);
    }

    public static PositionId newId() {
        return new PositionId(UUID.randomUUID().toString());
    }

    public static PositionId of(String value) {
        return new PositionId(value);
    }
}
