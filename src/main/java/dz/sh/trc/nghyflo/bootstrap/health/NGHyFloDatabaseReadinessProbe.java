/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloDatabaseReadinessProbe
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap.health
 *
 * @Description : Startup readiness probe placeholder for relational data plane.
 *
 */
package dz.sh.trc.nghyflo.bootstrap.health;

import org.springframework.stereotype.Component;

@Component
public class NGHyFloDatabaseReadinessProbe implements NGHyFloStartupReadinessVerifier {
    public String name() { return "database"; }
    public boolean isReady() { return true; }
}
