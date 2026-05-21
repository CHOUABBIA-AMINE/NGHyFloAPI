/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloTelemetryReadinessProbe
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap.health
 *
 * @Description : Startup readiness probe placeholder for telemetry ingress capabilities.
 *
 */
package dz.sh.trc.nghyflo.bootstrap.health;

import org.springframework.stereotype.Component;

@Component
public class NGHyFloTelemetryReadinessProbe implements NGHyFloStartupReadinessVerifier {
    public String name() { return "telemetry"; }
    public boolean isReady() { return true; }
}
