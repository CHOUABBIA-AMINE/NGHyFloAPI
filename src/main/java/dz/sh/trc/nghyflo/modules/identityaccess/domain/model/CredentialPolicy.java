package dz.sh.trc.nghyflo.modules.identityaccess.domain.model;

public class CredentialPolicy {
    private final int minimumLength;
    private final boolean requireRotation;

    public CredentialPolicy(int minimumLength, boolean requireRotation) {
        if (minimumLength < 8) {
            throw new IllegalArgumentException("minimumLength must be at least 8");
        }
        this.minimumLength = minimumLength;
        this.requireRotation = requireRotation;
    }

    public boolean accepts(String value) {
        return value != null && value.length() >= minimumLength;
    }

    public int minimumLength() {
        return minimumLength;
    }

    public boolean requireRotation() {
        return requireRotation;
    }
}
