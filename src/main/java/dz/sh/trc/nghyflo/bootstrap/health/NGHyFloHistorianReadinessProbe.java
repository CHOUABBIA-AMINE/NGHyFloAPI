/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloHistorianReadinessProbe
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap.health
 *
 * @Description : Startup readiness probe placeholder for historian connectivity readiness.
 *
 */
package dz.sh.trc.nghyflo.bootstrap.health;

import org.springframework.stereotype.Component;

@Component
public class NGHyFloHistorianReadinessProbe implements NGHyFloStartupReadinessVerifier {
    public String name() { return "historian"; }
    public boolean isReady() { return true; }
}
