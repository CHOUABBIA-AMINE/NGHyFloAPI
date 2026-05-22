/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : PasswordHash
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.domain.value
 *
 * @Description : .
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.domain.value;

public record PasswordHash(String value) {

    public PasswordHash {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("hash value is required");
        }
        if (!value.startsWith("{bcrypt}")) {
            throw new IllegalArgumentException("hash value must use the configured encoder prefix");
        }
    }
}
