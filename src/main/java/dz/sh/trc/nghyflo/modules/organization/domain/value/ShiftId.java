/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ShiftId
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : UUID-backed operational shift identifier.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

import java.util.UUID;

public record ShiftId(String value) {

    public ShiftId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("shiftId is required");
        }
        UUID.fromString(value);
    }

    public static ShiftId newId() {
        return new ShiftId(UUID.randomUUID().toString());
    }

    public static ShiftId of(String value) {
        return new ShiftId(value);
    }
}
