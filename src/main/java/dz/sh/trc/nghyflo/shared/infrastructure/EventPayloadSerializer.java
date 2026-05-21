package dz.sh.trc.nghyflo.shared.infrastructure;

public interface EventPayloadSerializer {
    String serialize(Object payload);

    <T> T deserialize(String payload, Class<T> targetType);
}
