/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : PlatformClock
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Interface
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.shared.infrastructure
 *
 * @Description : Shared infrastructure artifact PlatformClock.
 *
 */
package dz.sh.trc.nghyflo.shared.infrastructure;

import java.time.Instant;
public interface PlatformClock { Instant now(); }
