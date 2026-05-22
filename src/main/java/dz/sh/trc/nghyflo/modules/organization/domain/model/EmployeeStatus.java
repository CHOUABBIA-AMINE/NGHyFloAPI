/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EmployeeStatus
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Enum
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Employment availability state for operational staffing.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

public enum EmployeeStatus {
    ACTIVE,
    SUSPENDED,
    ON_LEAVE,
    RETIRED
}
