/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : UserController
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Controller
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.api.rest
 *
 * @Description : Exposes identity-access user endpoints and maps API requests to application commands.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.api.rest;

import dz.sh.trc.nghyflo.modules.identityaccess.api.rest.request.AssignRoleRequest;
import dz.sh.trc.nghyflo.modules.identityaccess.api.rest.request.CreateUserRequest;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.AssignRoleCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.CreateUserCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.dto.UserResponse;
import dz.sh.trc.nghyflo.modules.identityaccess.application.service.IdentityApplicationService;
import dz.sh.trc.nghyflo.shared.api.ApiResponse;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nghyflo/api/v1/users")
public class UserController {
    private final IdentityApplicationService service;

    public UserController(IdentityApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<UserResponse> create(@RequestBody CreateUserRequest request) {
        CreateUserCommand command = new CreateUserCommand(request.username(), request.credential());
        return ApiResponse.success(service.createUser(command), CorrelationId.newId());
    }

    @PostMapping("/{id}/roles")
    public ApiResponse<UserResponse> assignRole(@PathVariable String id, @RequestBody AssignRoleRequest request) {
        AssignRoleCommand command = new AssignRoleCommand(id, request.roleCode());
        return ApiResponse.success(service.assignRole(command), CorrelationId.newId());
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> list() {
        return ApiResponse.success(service.users(), CorrelationId.newId());
    }
}
