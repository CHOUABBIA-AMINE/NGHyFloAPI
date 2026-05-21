package dz.sh.trc.nghyflo.modules.identityaccess;

import dz.sh.trc.nghyflo.modules.identityaccess.application.command.AssignRoleCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.CreateUserCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.service.IdentityApplicationService;
import dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.InMemoryUserRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IdentityAccessApplicationServiceTest {
    @Test void createUserHashesPasswordAndAssignRole() {
        var service = new IdentityApplicationService(new InMemoryUserRepository());
        var created = service.createUser(new CreateUserCommand("operator1", "secret"));
        assertEquals("operator1", created.username());
        var updated = service.assignRole(new AssignRoleCommand(created.id(), "FLOW_APPROVER"));
        assertTrue(updated.roles().contains("FLOW_APPROVER"));
    }
}
