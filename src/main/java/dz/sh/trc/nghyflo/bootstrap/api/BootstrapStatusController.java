/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : BootstrapStatusController
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Controller
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.bootstrap.api
 *
 * @Description : Exposes platform bootstrap health status.
 *
 */
package dz.sh.trc.nghyflo.bootstrap.api;

import dz.sh.trc.nghyflo.bootstrap.NGHyFloApplicationIdentity;
import dz.sh.trc.nghyflo.bootstrap.health.NGHyFloStartupReadinessVerifier;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(NGHyFloApplicationIdentity.API_BASE_PATH + "/bootstrap")
public class BootstrapStatusController {
    private final Environment environment;
    private final List<NGHyFloStartupReadinessVerifier> readinessVerifiers;

    public BootstrapStatusController(Environment environment, List<NGHyFloStartupReadinessVerifier> readinessVerifiers) {
        this.environment = environment;
        this.readinessVerifiers = readinessVerifiers;
    }

    @GetMapping("/status")
    public BootstrapStatusResponse status() {
        boolean ready = readinessVerifiers.stream().allMatch(NGHyFloStartupReadinessVerifier::isReady);
        return new BootstrapStatusResponse(
                NGHyFloApplicationIdentity.SYSTEM_CODE,
                NGHyFloApplicationIdentity.SYSTEM_NAME,
                NGHyFloApplicationIdentity.FULL_NAME,
                NGHyFloApplicationIdentity.API_BASE_PATH,
                ready ? "UP" : "DEGRADED",
                Instant.now(),
                Arrays.asList(environment.getActiveProfiles())
        );
    }
}
