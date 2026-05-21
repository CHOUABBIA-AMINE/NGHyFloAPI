package dz.sh.trc.nghyflo.platform.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticatedActorResolver {
    // Request body actor identity is never trusted.
    public String currentActorId() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return a == null ? "anonymous" : a.getName();
    }
}
