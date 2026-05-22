/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CoverageEvaluationResponse
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.dto
 *
 * @Description : Result of checking whether an employee coverage set satisfies a requested coverage area.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.application.dto;

public record CoverageEvaluationResponse(
        String employeeId,
        boolean allowed,
        String reasonCode,
        String reasonMessage
) {
}
