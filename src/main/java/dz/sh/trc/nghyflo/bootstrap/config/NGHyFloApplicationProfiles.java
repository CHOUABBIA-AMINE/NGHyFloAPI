/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloApplicationProfiles
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap.config
 *
 * @Description : Defines supported runtime profiles for bootstrap hardening.
 *
 */
package dz.sh.trc.nghyflo.bootstrap.config;

public final class NGHyFloApplicationProfiles {
    public static final String DEVELOPMENT = "dev";
    public static final String TEST = "test";
    public static final String STAGING = "staging";
    public static final String PRODUCTION = "production";

    private NGHyFloApplicationProfiles() {
    }
}
