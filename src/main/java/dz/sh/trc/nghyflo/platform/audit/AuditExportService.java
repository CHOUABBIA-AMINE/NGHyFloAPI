/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditExportService
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Exports immutable audit evidence for compliance, investigation, and governance review.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

import java.time.Instant;

public interface AuditExportService {

    String exportTargetHistory(String targetType, String targetId, Instant fromInclusive, Instant toExclusive);
}
