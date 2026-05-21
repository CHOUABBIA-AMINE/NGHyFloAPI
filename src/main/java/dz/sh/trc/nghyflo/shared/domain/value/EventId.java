/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EventId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for EventId.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.util.UUID;

public record EventId(String value) {
    public EventId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("EventId is required");
        UUID.fromString(value);
    }
    public static EventId newId() { return new EventId(UUID.randomUUID().toString()); }
}
