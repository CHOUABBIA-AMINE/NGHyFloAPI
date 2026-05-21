/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SystemPlatformClock
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.shared.infrastructure
 *
 * @Description : Shared infrastructure artifact SystemPlatformClock.
 *
 */
package dz.sh.trc.nghyflo.shared.infrastructure;

import java.time.Instant;
public class SystemPlatformClock implements PlatformClock { public Instant now(){ return Instant.now(); } }
