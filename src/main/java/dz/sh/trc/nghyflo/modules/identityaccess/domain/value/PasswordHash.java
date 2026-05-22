package dz.sh.trc.nghyflo.modules.identityaccess.domain.value;

public record PasswordHash(String value) {

    public PasswordHash {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("hash value is required");
        }
        if (!value.startsWith("{bcrypt}")) {
            throw new IllegalArgumentException("hash value must use the configured encoder prefix");
        }
    }
}
