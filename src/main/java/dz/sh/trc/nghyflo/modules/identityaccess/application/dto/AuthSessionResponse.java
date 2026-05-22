/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuthSessionResponse
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.dto
 *
 * @Description : Session response returned after authentication or renewal.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.dto;

import java.time.Instant;
import java.util.Set;

public record AuthSessionResponse(
        String userId,
        String username,
        String sessionId,
        String accessArtifact,
        String renewalArtifact,
        Instant expiresAt,
        Set<String> roles
) {
}
