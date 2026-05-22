/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EvaluateCoverageCommand
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.command
 *
 * @Description : Command requesting coverage evaluation for an employee.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.application.command;

import dz.sh.trc.nghyflo.modules.organization.domain.model.StaffingCoverage;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;

public record EvaluateCoverageCommand(
        EmployeeId employeeId,
        StaffingCoverage requiredCoverage
) {

    public EvaluateCoverageCommand {
        if (employeeId == null) {
            throw new IllegalArgumentException("employeeId is required");
        }
        if (requiredCoverage == null) {
            throw new IllegalArgumentException("requiredCoverage is required");
        }
    }
}
