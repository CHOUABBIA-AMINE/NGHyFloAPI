package dz.sh.trc.nghyflo.modules.organization.domain.value;

public record PositionCode(String value) {

    public PositionCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("positionCode is required");
        }
        value = value.trim().toUpperCase();
    }

    public static PositionCode of(String value) {
        return new PositionCode(value);
    }
}
