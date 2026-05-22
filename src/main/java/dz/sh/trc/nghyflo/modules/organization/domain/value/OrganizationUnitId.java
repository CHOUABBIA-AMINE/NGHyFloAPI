/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationUnitId
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : UUID-backed organization unit identifier.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

import java.util.UUID;

public record OrganizationUnitId(String value) {

    public OrganizationUnitId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("organizationUnitId is required");
        }
        UUID.fromString(value);
    }

    public static OrganizationUnitId newId() {
        return new OrganizationUnitId(UUID.randomUUID().toString());
    }

    public static OrganizationUnitId of(String value) {
        return new OrganizationUnitId(value);
    }
}
