package dz.sh.trc.nghyflo.bootstrap;

import dz.sh.trc.nghyflo.bootstrap.api.BootstrapStatusResponse;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BootstrapStatusControllerTest {
    @Test
    void shouldExposeIdentityConstants() {
        BootstrapStatusResponse response = new BootstrapStatusResponse(
                NGHyFloApplicationIdentity.SYSTEM_CODE,
                NGHyFloApplicationIdentity.SYSTEM_NAME,
                NGHyFloApplicationIdentity.FULL_NAME,
                NGHyFloApplicationIdentity.API_BASE_PATH,
                "UP",
                Instant.now(),
                List.of("test")
        );
        assertEquals("NGHYFLO", response.systemCode());
        assertEquals("/nghyflo/api/v1", response.apiBasePath());
    }
}
