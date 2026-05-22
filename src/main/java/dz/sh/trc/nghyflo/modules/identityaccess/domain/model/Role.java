package dz.sh.trc.nghyflo.modules.identityaccess.domain.model;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PermissionCode;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.RoleCode;
import java.util.HashSet;
import java.util.Set;

public class Role {
    private final RoleCode code;
    private final String description;
    private final Set<PermissionCode> permissions = new HashSet<>();

    public Role(RoleCode code, String description) {
        if (code == null) {
            throw new IllegalArgumentException("role code is required");
        }
        this.code = code;
        this.description = description == null ? "" : description.trim();
    }

    public void addPermission(PermissionCode permissionCode) {
        if (permissionCode == null) {
            throw new IllegalArgumentException("permission code is required");
        }
        permissions.add(permissionCode);
    }

    public boolean hasPermission(PermissionCode permissionCode) {
        return permissions.contains(permissionCode);
    }

    public RoleCode code() {
        return code;
    }

    public String description() {
        return description;
    }

    public Set<PermissionCode> permissions() {
        return Set.copyOf(permissions);
    }
}
