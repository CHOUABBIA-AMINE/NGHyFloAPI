/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EmployeeLookupPort
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.port
 *
 * @Description : Application port for resolving organization employees.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.application.port;

import dz.sh.trc.nghyflo.modules.organization.domain.model.Employee;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import java.util.Optional;

public interface EmployeeLookupPort {

    Optional<Employee> findById(EmployeeId employeeId);
}
