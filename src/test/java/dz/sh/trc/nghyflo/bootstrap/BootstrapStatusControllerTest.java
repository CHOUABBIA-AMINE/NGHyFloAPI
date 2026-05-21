/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : BootstrapStatusControllerTest
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap
 *
 * @Description : Verifies bootstrap status responses and readiness aggregation behavior.
 *
 */
package dz.sh.trc.nghyflo.bootstrap;

import dz.sh.trc.nghyflo.bootstrap.api.BootstrapStatusController;
import dz.sh.trc.nghyflo.bootstrap.api.BootstrapStatusResponse;
import dz.sh.trc.nghyflo.bootstrap.health.NGHyFloStartupReadinessVerifier;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BootstrapStatusControllerTest {

    @Test
    void shouldReturnUpWhenAllReadinessProbesAreReady() {
        MockEnvironment environment = new MockEnvironment();
        environment.setActiveProfiles("test");
        List<NGHyFloStartupReadinessVerifier> probes = List.of(
                new Probe("database", true),
                new Probe("telemetry", true)
        );

        BootstrapStatusController controller = new BootstrapStatusController(environment, probes);
        BootstrapStatusResponse response = controller.status();

        assertEquals("NGHYFLO", response.systemCode());
        assertEquals("UP", response.status());
        assertEquals("/nghyflo/api/v1", response.apiBasePath());
    }

    @Test
    void shouldReturnDegradedWhenAnyReadinessProbeIsNotReady() {
        MockEnvironment environment = new MockEnvironment();
        List<NGHyFloStartupReadinessVerifier> probes = List.of(
                new Probe("database", true),
                new Probe("historian", false)
        );

        BootstrapStatusController controller = new BootstrapStatusController(environment, probes);
        BootstrapStatusResponse response = controller.status();

        assertEquals("DEGRADED", response.status());
    }

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

    private record Probe(String name, boolean ready) implements NGHyFloStartupReadinessVerifier {
        @Override
        public boolean isReady() {
            return ready;
        }
    }
}
