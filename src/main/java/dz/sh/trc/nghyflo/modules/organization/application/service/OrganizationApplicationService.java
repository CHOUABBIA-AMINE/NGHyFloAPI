/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationApplicationService
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-30
 *
 * @Type        : Service
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.service
 *
 * @Description : Coordinates in-memory organization use cases for regions, structures, and employees.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.application.service;

import dz.sh.trc.nghyflo.modules.organization.application.command.AssignEmployeeToStructureCommand;
import dz.sh.trc.nghyflo.modules.organization.application.command.CreateEmployeeCommand;
import dz.sh.trc.nghyflo.modules.organization.application.command.CreateRegionCommand;
import dz.sh.trc.nghyflo.modules.organization.application.command.CreateStructureCommand;
import dz.sh.trc.nghyflo.modules.organization.application.dto.EmployeeScopeResponse;
import dz.sh.trc.nghyflo.modules.organization.domain.model.Employee;
import dz.sh.trc.nghyflo.modules.organization.domain.model.Region;
import dz.sh.trc.nghyflo.modules.organization.domain.model.Structure;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.StructureId;
import dz.sh.trc.nghyflo.shared.domain.value.RegionId;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class OrganizationApplicationService {
    private final Map<String, Region> regions = new HashMap<>();
    private final Map<String, Structure> structures = new HashMap<>();
    private final Map<String, Employee> employees = new HashMap<>();

    public Region createRegion(CreateRegionCommand command) {
        Region region = new Region(RegionId.newId(), command.name());
        regions.put(region.id().value(), region);
        return region;
    }

    public Structure createStructure(CreateStructureCommand command) {
        Structure structure = new Structure(StructureId.newId(), new RegionId(command.regionId()), command.name());
        structures.put(structure.id().value(), structure);
        return structure;
    }

    public Employee createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(EmployeeId.newId(), command.fullName());
        employees.put(employee.id().value(), employee);
        return employee;
    }

    public Employee assignEmployeeToStructure(AssignEmployeeToStructureCommand command) {
        Employee employee = employees.get(command.employeeId());
        if (employee == null) {
            throw new NoSuchElementException("Employee not found");
        }
        employee.assignStructure(new StructureId(command.structureId()));
        return employee;
    }

    public EmployeeScopeResponse getEmployeeScope(String employeeId) {
        Employee employee = employees.get(employeeId);
        if (employee == null) {
            throw new NoSuchElementException("Employee not found");
        }
        Structure structure = structures.get(employee.structureId().value());
        return new EmployeeScopeResponse(
                employee.id().value(),
                structure.regionId().value(),
                structure.id().value(),
                null,
                null,
                null
        );
    }
}
