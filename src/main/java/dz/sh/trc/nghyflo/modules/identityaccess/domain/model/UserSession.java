/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : UserSession
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.domain.model
 *
 * @Description : Represents a user session lifecycle in NGHyFlo identity access.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.domain.model;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.SessionId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;

public class UserSession {
    private final SessionId id;
    private final UserId userId;
    private final Instant openedAt;
    private Instant closedAt;
    private boolean active;

    public UserSession(SessionId id, UserId userId, Instant openedAt) {
        if (id == null) {
            throw new IllegalArgumentException("session id is required");
        }
        if (userId == null) {
            throw new IllegalArgumentException("user id is required");
        }
        if (openedAt == null) {
            throw new IllegalArgumentException("openedAt is required");
        }
        this.id = id;
        this.userId = userId;
        this.openedAt = openedAt;
        this.active = true;
    }

    public static UserSession open(UserId userId) {
        return new UserSession(SessionId.newId(), userId, Instant.now());
    }

    public void close(Instant closedAt) {
        if (closedAt == null || closedAt.isBefore(openedAt)) {
            throw new IllegalArgumentException("closedAt must be after openedAt");
        }
        this.closedAt = closedAt;
        this.active = false;
    }

    public SessionId id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public boolean active() {
        return active;
    }

    public Instant openedAt() {
        return openedAt;
    }

    public Instant closedAt() {
        return closedAt;
    }
}
