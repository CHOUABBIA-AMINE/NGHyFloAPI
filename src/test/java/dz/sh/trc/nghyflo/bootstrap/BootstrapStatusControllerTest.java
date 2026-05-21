package dz.sh.trc.nghyflo.bootstrap;

import dz.sh.trc.nghyflo.bootstrap.api.BootstrapStatusController;
import dz.sh.trc.nghyflo.bootstrap.api.BootstrapStatusResponse;
import dz.sh.trc.nghyflo.bootstrap.health.NGHyFloStartupReadinessVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BootstrapStatusControllerTest {

    @Test
    void shouldReturnUpWhenAllReadinessProbesAreReady() {
        MockEnvironment environment = new MockEnvironment();
        environment.setActiveProfiles("test");
        List<NGHyFloStartupReadinessVerifier> probes = List.of(new Probe("database", true), new Probe("telemetry", true));

        BootstrapStatusController controller = new BootstrapStatusController(environment, probes);
        BootstrapStatusResponse response = controller.status();

        assertEquals("NGHYFLO", response.systemCode());
        assertEquals("UP", response.status());
        assertEquals("/nghyflo/api/v1", response.apiBasePath());
    }

    @Test
    void shouldReturnDegradedWhenAnyReadinessProbeIsNotReady() {
        MockEnvironment environment = new MockEnvironment();
        List<NGHyFloStartupReadinessVerifier> probes = List.of(new Probe("database", true), new Probe("historian", false));

        BootstrapStatusController controller = new BootstrapStatusController(environment, probes);
        BootstrapStatusResponse response = controller.status();

        assertEquals("DEGRADED", response.status());
    }

    private record Probe(String name, boolean ready) implements NGHyFloStartupReadinessVerifier {
        @Override public boolean isReady() { return ready; }
    }
}
