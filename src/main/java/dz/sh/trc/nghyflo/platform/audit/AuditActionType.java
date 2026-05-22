/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditActionType
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Enum
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Classifies auditable operational, security, workflow, and integration actions.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

public enum AuditActionType {
    CREATE,
    UPDATE,
    DELETE,
    SUBMIT,
    VALIDATE,
    APPROVE,
    REJECT,
    PUBLISH,
    ACKNOWLEDGE,
    ESCALATE,
    RESOLVE,
    LOGIN,
    LOGOUT,
    PRIVILEGED_ACTION,
    INTEGRATION_EXCHANGE
}
