package dz.sh.trc.nghyflo.modules.organization;

import dz.sh.trc.nghyflo.modules.organization.application.command.*;
import dz.sh.trc.nghyflo.modules.organization.application.service.OrganizationApplicationService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationApplicationServiceTest {
    @Test void employeeScopeResolutionWorks() {
        var s = new OrganizationApplicationService();
        var r = s.createRegion(new CreateRegionCommand("Hassi Messaoud"));
        var st = s.createStructure(new CreateStructureCommand(r.id().value(), "TRC-Structure-1"));
        var e = s.createEmployee(new CreateEmployeeCommand("Operator A"));
        s.assignEmployeeToStructure(new AssignEmployeeToStructureCommand(e.id().value(), st.id().value()));
        var scope = s.getEmployeeScope(e.id().value());
        assertEquals(st.id().value(), scope.structureId());
        assertEquals(r.id().value(), scope.regionId());
    }
}
