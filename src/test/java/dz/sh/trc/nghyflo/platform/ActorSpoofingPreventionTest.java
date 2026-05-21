package dz.sh.trc.nghyflo.platform;

import dz.sh.trc.nghyflo.platform.security.authentication.AuthenticatedActorResolver;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.junit.jupiter.api.Assertions.*;

class ActorSpoofingPreventionTest {
    @Test void usesSecurityContextIdentity() {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("trusted-user", "n/a"));
        assertEquals("trusted-user", new AuthenticatedActorResolver().currentActorId());
    }
}
