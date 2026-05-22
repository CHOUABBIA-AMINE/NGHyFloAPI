/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Username
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.domain.value
 *
 * @Description : Validated username value object for NGHyFlo identity access.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.domain.value;

public record Username(String value) {

    public Username {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (value.length() < 3) {
            throw new IllegalArgumentException("Username too short");
        }
        value = value.trim();
    }
}
