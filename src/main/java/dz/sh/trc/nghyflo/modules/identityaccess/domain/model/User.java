/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : User
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
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
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.RoleCode;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.Username;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class User {
    private final UserId id;
    private final Username username;
    private final PasswordHash passwordHash;
    private final Set<RoleCode> roleCodes = new HashSet<>();
    private final Instant createdAt;

    public User(UserId id, Username username, PasswordHash passwordHash, Instant createdAt) {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (username == null) {
            throw new IllegalArgumentException("username is required");
        }
        if (passwordHash == null) {
            throw new IllegalArgumentException("passwordHash is required");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt is required");
        }
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public void assignRole(String roleCode) {
        assignRole(RoleCode.of(roleCode));
    }

    public void assignRole(RoleCode roleCode) {
        if (roleCode == null) {
            throw new IllegalArgumentException("roleCode is required");
        }
        roleCodes.add(roleCode);
    }

    public boolean hasRole(RoleCode roleCode) {
        return roleCodes.contains(roleCode);
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

    public Set<RoleCode> roleCodes() {
        return Set.copyOf(roleCodes);
    }

    public Set<String> roles() {
        return roleCodes.stream().map(RoleCode::value).collect(Collectors.toUnmodifiableSet());
    }

    public Instant createdAt() {
        return createdAt;
    }
}
