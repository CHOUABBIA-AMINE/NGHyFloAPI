package dz.sh.trc.nghyflo.modules.identityaccess.domain.value;

public record Username(String value) {
    public Username {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("Username is required");
        if (value.length() < 3) throw new IllegalArgumentException("Username too short");
    }
}
