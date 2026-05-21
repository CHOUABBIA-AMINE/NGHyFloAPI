/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : TenantId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for an NGHyFlo tenant boundary.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.util.UUID;

public record TenantId(String value) {

    public TenantId {
        value = requireValidUuid(value, "TenantId");
    }

    public static TenantId newId() {
        return new TenantId(UUID.randomUUID().toString());
    }

    public static TenantId of(String value) {
        return new TenantId(value);
    }

    private static String requireValidUuid(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        UUID.fromString(value);
        return value;
    }
}
