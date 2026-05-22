package dz.sh.trc.nghyflo.modules.identityaccess.domain.model;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.RoleCode;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.util.HashSet;
import java.util.Set;

public class Group {
    private final String code;
    private final Set<UserId> members = new HashSet<>();
    private final Set<RoleCode> roles = new HashSet<>();

    public Group(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("group code is required");
        }
        this.code = code.trim().toUpperCase();
    }

    public void addMember(UserId userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }
        members.add(userId);
    }

    public void addRole(RoleCode roleCode) {
        if (roleCode == null) {
            throw new IllegalArgumentException("roleCode is required");
        }
        roles.add(roleCode);
    }

    public String code() {
        return code;
    }

    public Set<UserId> members() {
        return Set.copyOf(members);
    }

    public Set<RoleCode> roles() {
        return Set.copyOf(roles);
    }
}
