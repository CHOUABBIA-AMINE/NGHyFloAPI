package dz.sh.trc.nghyflo.modules.organization.application.service;

import dz.sh.trc.nghyflo.modules.organization.application.command.*;
import dz.sh.trc.nghyflo.modules.organization.application.dto.EmployeeScopeResponse;
import dz.sh.trc.nghyflo.modules.organization.domain.model.*;
import dz.sh.trc.nghyflo.shared.domain.value.*;
import java.util.*;

public class OrganizationApplicationService {
    private final Map<String, Region> regions = new HashMap<>();
    private final Map<String, Structure> structures = new HashMap<>();
    private final Map<String, Employee> employees = new HashMap<>();

    public Region createRegion(CreateRegionCommand c) {
        Region r = new Region(new RegionId(java.util.UUID.randomUUID().toString()), c.name());
        regions.put(r.id().value(), r);
        return r;
    }

    public Structure createStructure(CreateStructureCommand c) {
        Structure s = new Structure(new StructureId(java.util.UUID.randomUUID().toString()), new RegionId(c.regionId()), c.name());
        structures.put(s.id().value(), s);
        return s;
    }

    public Employee createEmployee(CreateEmployeeCommand c) {
        Employee e = new Employee(new EmployeeId(java.util.UUID.randomUUID().toString()), c.fullName());
        employees.put(e.id().value(), e);
        return e;
    }

    public Employee assignEmployeeToStructure(AssignEmployeeToStructureCommand c) {
        Employee e = employees.get(c.employeeId());
        if (e == null) throw new NoSuchElementException("Employee not found");
        e.assignStructure(new StructureId(c.structureId()));
        return e;
    }

    public EmployeeScopeResponse getEmployeeScope(String employeeId) {
        Employee e = employees.get(employeeId);
        if (e == null) throw new NoSuchElementException("Employee not found");
        Structure s = structures.get(e.structureId().value());
        return new EmployeeScopeResponse(e.id().value(), s.regionId().value(), s.id().value(), null, null, null);
    }
}
