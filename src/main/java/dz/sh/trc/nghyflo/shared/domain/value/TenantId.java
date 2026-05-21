/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : TenantId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for TenantId.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.util.UUID;

public record TenantId(String value) {
    public TenantId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("TenantId is required");
        UUID.fromString(value);
    }
    public static TenantId newId() { return new TenantId(UUID.randomUUID().toString()); }
}
