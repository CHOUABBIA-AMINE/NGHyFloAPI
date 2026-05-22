/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CoverageAllocationPort
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.port
 *
 * @Description : Application port for resolving active employee coverage allocations.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.application.port;

import dz.sh.trc.nghyflo.modules.organization.domain.model.CoverageAllocation;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import java.util.List;

public interface CoverageAllocationPort {

    List<CoverageAllocation> findActiveByEmployeeId(EmployeeId employeeId);
}
