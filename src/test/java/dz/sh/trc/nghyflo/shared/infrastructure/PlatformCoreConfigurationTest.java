package dz.sh.trc.nghyflo.shared.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        ObjectMapperConfiguration.class,
        PlatformCoreConfiguration.class,
        JacksonJsonSerializer.class,
        JacksonEventPayloadSerializer.class
})
class PlatformCoreConfigurationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlatformClock platformClock;

    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Autowired
    private JsonSerializer jsonSerializer;

    @Autowired
    private EventPayloadSerializer eventPayloadSerializer;

    @Test
    void shouldProvideOperationalCoreBeans() {
        assertThat(objectMapper).isNotNull();
        assertThat(platformClock.now()).isBeforeOrEqualTo(Instant.now());
        assertThat(identifierGenerator.nextId()).isNotBlank();
        assertThat(jsonSerializer.toJson(new ProbePayload("ok"))).contains("ok");
        assertThat(eventPayloadSerializer.serialize(new ProbePayload("ready"))).contains("ready");
    }

    private record ProbePayload(String status) {
    }
}
