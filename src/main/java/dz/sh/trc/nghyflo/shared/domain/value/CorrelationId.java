/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CorrelationId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for CorrelationId.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.util.UUID;

public record CorrelationId(String value) {
    public CorrelationId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("CorrelationId is required");
        UUID.fromString(value);
    }
    public static CorrelationId newId() { return new CorrelationId(UUID.randomUUID().toString()); }
}
