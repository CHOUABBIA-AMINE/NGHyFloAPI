/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloModuleReadinessProbe
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap.health
 *
 * @Description : Startup readiness probe placeholder for bounded-context module wiring.
 *
 */
package dz.sh.trc.nghyflo.bootstrap.health;

import org.springframework.stereotype.Component;

@Component
public class NGHyFloModuleReadinessProbe implements NGHyFloStartupReadinessVerifier {
    public String name() { return "modules"; }
    public boolean isReady() { return true; }
}
