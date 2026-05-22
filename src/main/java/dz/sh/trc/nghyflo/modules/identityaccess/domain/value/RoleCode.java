/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : RoleCode
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.domain.value
 *
 * @Description : Stable role code used by NGHyFlo identity-access policies.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.domain.value;

public record RoleCode(String value) {

    public RoleCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("roleCode is required");
        }
        value = value.trim().toUpperCase();
    }

    public static RoleCode of(String value) {
        return new RoleCode(value);
    }
}
