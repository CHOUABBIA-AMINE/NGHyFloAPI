package dz.sh.trc.nghyflo.modules.identityaccess.domain.model;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.Username;
import java.time.Instant;

public class AccessLogEntry {
    private final Username username;
    private final Instant occurredAt;
    private final boolean accepted;
    private final String sourceAddress;

    public AccessLogEntry(Username username, Instant occurredAt, boolean accepted, String sourceAddress) {
        if (username == null) {
            throw new IllegalArgumentException("username is required");
        }
        if (occurredAt == null) {
            throw new IllegalArgumentException("occurredAt is required");
        }
        this.username = username;
        this.occurredAt = occurredAt;
        this.accepted = accepted;
        this.sourceAddress = sourceAddress == null ? "unknown" : sourceAddress.trim();
    }

    public Username username() {
        return username;
    }

    public Instant occurredAt() {
        return occurredAt;
    }

    public boolean accepted() {
        return accepted;
    }

    public String sourceAddress() {
        return sourceAddress;
    }
}
