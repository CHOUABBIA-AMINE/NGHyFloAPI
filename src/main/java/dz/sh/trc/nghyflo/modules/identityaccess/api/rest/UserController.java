package dz.sh.trc.nghyflo.modules.identityaccess.api.rest;

import dz.sh.trc.nghyflo.modules.identityaccess.application.command.AssignRoleCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.CreateUserCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.dto.UserResponse;
import dz.sh.trc.nghyflo.modules.identityaccess.application.service.IdentityApplicationService;
import dz.sh.trc.nghyflo.shared.api.ApiResponse;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nghyflo/api/v1/users")
public class UserController {
    private final IdentityApplicationService service;
    public UserController(IdentityApplicationService service) { this.service = service; }

    @PostMapping
    public ApiResponse<UserResponse> create(@RequestBody CreateUserCommand command) {
        return ApiResponse.success(service.createUser(command), CorrelationId.newId());
    }

    @PostMapping("/{id}/roles")
    public ApiResponse<UserResponse> assignRole(@PathVariable String id, @RequestBody AssignRoleBody body) {
        return ApiResponse.success(service.assignRole(new AssignRoleCommand(id, body.roleCode())), CorrelationId.newId());
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> list() { return ApiResponse.success(service.users(), CorrelationId.newId()); }

    public record AssignRoleBody(String roleCode) {}
}
