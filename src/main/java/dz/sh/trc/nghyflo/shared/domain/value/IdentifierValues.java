/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : IdentifierValues
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Central validation utility for UUID-backed NGHyFlo shared-kernel identifiers.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.util.UUID;

final class IdentifierValues {

    private IdentifierValues() {
    }

    static String requireUuid(String value, String identifierName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(identifierName + " is required");
        }
        UUID.fromString(value);
        return value;
    }

    static String newUuid() {
        return UUID.randomUUID().toString();
    }
}
