package dz.sh.trc.nghyflo.shared.infrastructure;

public interface JsonSerializer {
    String toJson(Object value);

    <T> T fromJson(String payload, Class<T> targetType);
}
