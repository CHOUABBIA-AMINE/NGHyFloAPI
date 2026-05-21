package dz.sh.trc.nghyflo.modules.organization.api.rest;

import dz.sh.trc.nghyflo.modules.organization.application.command.AssignEmployeeToStructureCommand;
import dz.sh.trc.nghyflo.modules.organization.application.command.CreateEmployeeCommand;
import dz.sh.trc.nghyflo.modules.organization.application.dto.EmployeeScopeResponse;
import dz.sh.trc.nghyflo.modules.organization.application.service.OrganizationApplicationService;
import dz.sh.trc.nghyflo.shared.api.ApiResponse;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nghyflo/api/v1/employees")
public class EmployeeController {
    private final OrganizationApplicationService service;
    public EmployeeController(OrganizationApplicationService service) { this.service = service; }

    @PostMapping
    public ApiResponse<String> create(@RequestBody CreateEmployeeCommand cmd) {
        return ApiResponse.success(service.createEmployee(cmd).id().value(), CorrelationId.newId());
    }

    @PostMapping("/{id}/structure")
    public ApiResponse<String> assign(@PathVariable String id, @RequestBody AssignBody body) {
        return ApiResponse.success(service.assignEmployeeToStructure(new AssignEmployeeToStructureCommand(id, body.structureId())).id().value(), CorrelationId.newId());
    }

    @GetMapping("/{id}/operational-scope")
    public ApiResponse<EmployeeScopeResponse> scope(@PathVariable String id) {
        return ApiResponse.success(service.getEmployeeScope(id), CorrelationId.newId());
    }

    public record AssignBody(String structureId) {}
}
