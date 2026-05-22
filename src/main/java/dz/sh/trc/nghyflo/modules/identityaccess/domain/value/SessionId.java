/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SessionId
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.domain.value
 *
 * @Description : Stable session identifier for NGHyFlo identity-access sessions.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.domain.value;

import java.util.UUID;

public record SessionId(String value) {

    public SessionId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("sessionId is required");
        }
        UUID.fromString(value);
    }

    public static SessionId newId() {
        return new SessionId(UUID.randomUUID().toString());
    }

    public static SessionId of(String value) {
        return new SessionId(value);
    }
}
