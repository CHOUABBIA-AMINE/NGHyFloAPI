/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : PermissionCode
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.domain.value
 *
 * @Description : Stable permission code used by NGHyFlo identity-access authorization policies.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.domain.value;

public record PermissionCode(String value) {

    public PermissionCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("permissionCode is required");
        }
        value = value.trim().toUpperCase();
    }

    public static PermissionCode of(String value) {
        return new PermissionCode(value);
    }
}
