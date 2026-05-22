/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditEvidenceStore
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Stores and retrieves immutable audit evidence for compliance and investigation.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

import java.util.Optional;

public interface AuditEvidenceStore {

    AuditRecord append(AuditRecord record);

    Optional<AuditRecord> findByAuditId(String auditId);

    Optional<String> latestHashForTarget(String targetType, String targetId);
}
