package dz.sh.trc.nghyflo.modules.identityaccess.domain.model;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.SessionId;
import java.time.Instant;

public class RefreshToken {
    private final String tokenHash;
    private final SessionId sessionId;
    private final Instant expiresAt;
    private boolean revoked;

    public RefreshToken(String tokenHash, SessionId sessionId, Instant expiresAt) {
        if (tokenHash == null || tokenHash.isBlank()) {
            throw new IllegalArgumentException("tokenHash is required");
        }
        if (sessionId == null) {
            throw new IllegalArgumentException("sessionId is required");
        }
        if (expiresAt == null) {
            throw new IllegalArgumentException("expiresAt is required");
        }
        this.tokenHash = tokenHash.trim();
        this.sessionId = sessionId;
        this.expiresAt = expiresAt;
    }

    public void revoke() {
        this.revoked = true;
    }

    public boolean usableAt(Instant instant) {
        return !revoked && instant != null && instant.isBefore(expiresAt);
    }

    public String tokenHash() {
        return tokenHash;
    }

    public SessionId sessionId() {
        return sessionId;
    }

    public Instant expiresAt() {
        return expiresAt;
    }

    public boolean revoked() {
        return revoked;
    }
}
