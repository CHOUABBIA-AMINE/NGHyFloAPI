/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationCoverageService
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.organization.application.service
 *
 * @Description : Evaluates whether organization staffing coverage satisfies requested operational coverage.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.application.service;

import dz.sh.trc.nghyflo.modules.organization.application.command.EvaluateCoverageCommand;
import dz.sh.trc.nghyflo.modules.organization.application.command.EvaluateOperationalScopeCommand;
import dz.sh.trc.nghyflo.modules.organization.application.dto.CoverageEvaluationResponse;
import dz.sh.trc.nghyflo.modules.organization.application.mapper.OperationalScopeCoverageMapper;
import dz.sh.trc.nghyflo.modules.organization.application.port.CoverageAllocationPort;
import dz.sh.trc.nghyflo.modules.organization.application.port.EmployeeLookupPort;
import dz.sh.trc.nghyflo.modules.organization.domain.model.AssignmentStatus;
import dz.sh.trc.nghyflo.modules.organization.domain.model.CoverageAllocation;
import dz.sh.trc.nghyflo.modules.organization.domain.model.Employee;
import java.util.List;

public class OrganizationCoverageService {
    private final EmployeeLookupPort employees;
    private final CoverageAllocationPort allocations;
    private final OperationalScopeCoverageMapper scopeMapper;

    public OrganizationCoverageService(EmployeeLookupPort employees, CoverageAllocationPort allocations) {
        this(employees, allocations, new OperationalScopeCoverageMapper());
    }

    public OrganizationCoverageService(
            EmployeeLookupPort employees,
            CoverageAllocationPort allocations,
            OperationalScopeCoverageMapper scopeMapper
    ) {
        if (employees == null) {
            throw new IllegalArgumentException("employees port is required");
        }
        if (allocations == null) {
            throw new IllegalArgumentException("allocations port is required");
        }
        if (scopeMapper == null) {
            throw new IllegalArgumentException("scopeMapper is required");
        }
        this.employees = employees;
        this.allocations = allocations;
        this.scopeMapper = scopeMapper;
    }

    public CoverageEvaluationResponse evaluate(EvaluateOperationalScopeCommand command) {
        return evaluate(new EvaluateCoverageCommand(command.employeeId(), scopeMapper.toCoverage(command.requiredScope())));
    }

    public CoverageEvaluationResponse evaluate(EvaluateCoverageCommand command) {
        Employee employee = employees.findById(command.employeeId()).orElse(null);
        if (employee == null) {
            return denied(command, "EMPLOYEE_NOT_FOUND", "Employee was not found");
        }
        if (!employee.activeInStructure()) {
            return denied(command, "EMPLOYEE_NOT_ACTIVE_IN_STRUCTURE", "Employee is not active in a structure");
        }
        List<CoverageAllocation> activeAllocations = allocations.findActiveByEmployeeId(command.employeeId())
                .stream()
                .filter(allocation -> allocation.status() == AssignmentStatus.ACTIVE)
                .toList();
        if (activeAllocations.isEmpty()) {
            return denied(command, "NO_ACTIVE_COVERAGE", "Employee has no active coverage allocation");
        }
        boolean covered = activeAllocations.stream().anyMatch(allocation -> allocation.includes(command.requiredCoverage()));
        if (!covered) {
            return denied(command, "COVERAGE_DENIED", "Employee coverage does not include requested area");
        }
        return new CoverageEvaluationResponse(
                command.employeeId().value(),
                true,
                "COVERAGE_GRANTED",
                "Employee coverage includes requested area"
        );
    }

    private CoverageEvaluationResponse denied(EvaluateCoverageCommand command, String reasonCode, String reasonMessage) {
        return new CoverageEvaluationResponse(command.employeeId().value(), false, reasonCode, reasonMessage);
    }
}
