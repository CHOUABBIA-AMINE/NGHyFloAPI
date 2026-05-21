package dz.sh.trc.nghyflo.platform.security.authorization;

import java.time.Instant;

public record PolicyDecision(boolean allowed, String reasonCode, String reasonMessage, Instant evaluatedAt) {
    public static PolicyDecision allow(String code, String message) { return new PolicyDecision(true, code, message, Instant.now()); }
    public static PolicyDecision deny(String code, String message) { return new PolicyDecision(false, code, message, Instant.now()); }
}
