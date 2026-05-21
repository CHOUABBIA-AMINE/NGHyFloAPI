/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : User
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.domain.model
 *
 * @Description : Represents an authenticated NGHyFlo operator or service account principal.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.domain.model;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PasswordHash;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.Username;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final UserId id;
    private final Username username;
    private final PasswordHash passwordHash;
    private final Set<String> roles = new HashSet<>();
    private final Instant createdAt;

    public User(UserId id, Username username, PasswordHash passwordHash, Instant createdAt) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public void assignRole(String roleCode) {
        roles.add(roleCode);
    }

    public UserId id() {
        return id;
    }

    public Username username() {
        return username;
    }

    public PasswordHash passwordHash() {
        return passwordHash;
    }

    public Set<String> roles() {
        return Set.copyOf(roles);
    }

    public Instant createdAt() {
        return createdAt;
    }
}
