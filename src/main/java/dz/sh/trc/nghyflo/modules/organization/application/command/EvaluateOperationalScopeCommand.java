/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EvaluateOperationalScopeCommand
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.command
 *
 * @Description : Command requesting organization coverage evaluation from a platform operational scope.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.application.command;

import dz.sh.trc.nghyflo.platform.security.authorization.OperationalScope;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;

public record EvaluateOperationalScopeCommand(
        EmployeeId employeeId,
        OperationalScope requiredScope
) {

    public EvaluateOperationalScopeCommand {
        if (employeeId == null) {
            throw new IllegalArgumentException("employeeId is required");
        }
        if (requiredScope == null) {
            throw new IllegalArgumentException("requiredScope is required");
        }
    }
}
