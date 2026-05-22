/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuthResponseDtoTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.dto
 *
 * @Description : Verifies authentication response DTO construction.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.dto;

import java.time.Instant;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthResponseDtoTest {

    @Test
    void shouldCreateSessionResponse() {
        AuthSessionResponse response = new AuthSessionResponse(
                "user-1",
                "operator.one",
                "session-1",
                "access-artifact",
                "renewal-artifact",
                Instant.parse("2026-05-22T10:15:00Z"),
                Set.of("ROLE_OPERATOR")
        );

        assertEquals("user-1", response.userId());
        assertEquals("operator.one", response.username());
        assertEquals("session-1", response.sessionId());
        assertEquals("access-artifact", response.accessArtifact());
        assertEquals("renewal-artifact", response.renewalArtifact());
        assertTrue(response.roles().contains("ROLE_OPERATOR"));
    }

    @Test
    void shouldCreateLogoutResponse() {
        LogoutResponse response = new LogoutResponse("session-1", true);

        assertEquals("session-1", response.sessionId());
        assertTrue(response.terminated());
    }
}
