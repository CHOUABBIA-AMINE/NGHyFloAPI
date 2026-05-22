/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditRetentionPolicy
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Defines retention rules for immutable audit evidence and compliance exports.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

import java.time.Duration;

public interface AuditRetentionPolicy {

    Duration retentionPeriodFor(AuditActionType actionType);

    boolean exportRequired(AuditActionType actionType);
}
