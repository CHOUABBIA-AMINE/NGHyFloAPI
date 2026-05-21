package dz.sh.trc.nghyflo.shared.infrastructure;

import org.springframework.stereotype.Component;

@Component
public class JacksonEventPayloadSerializer implements EventPayloadSerializer {

    private final JsonSerializer jsonSerializer;

    public JacksonEventPayloadSerializer(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    @Override
    public String serialize(Object payload) {
        return jsonSerializer.toJson(payload);
    }

    @Override
    public <T> T deserialize(String payload, Class<T> targetType) {
        return jsonSerializer.fromJson(payload, targetType);
    }
}
