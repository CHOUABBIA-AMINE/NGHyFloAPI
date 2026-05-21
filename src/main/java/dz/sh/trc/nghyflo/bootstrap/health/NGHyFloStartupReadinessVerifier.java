/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloStartupReadinessVerifier
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Interface
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap.health
 *
 * @Description : Contract for readiness probes participating in startup validation.
 *
 */
package dz.sh.trc.nghyflo.bootstrap.health;

public interface NGHyFloStartupReadinessVerifier {
    String name();
    boolean isReady();
}
