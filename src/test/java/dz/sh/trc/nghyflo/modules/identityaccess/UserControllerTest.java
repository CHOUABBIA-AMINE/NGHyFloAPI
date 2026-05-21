package dz.sh.trc.nghyflo.modules.identityaccess;

import dz.sh.trc.nghyflo.modules.identityaccess.api.rest.UserController;
import dz.sh.trc.nghyflo.modules.identityaccess.application.command.CreateUserCommand;
import dz.sh.trc.nghyflo.modules.identityaccess.application.service.IdentityApplicationService;
import dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.InMemoryUserRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    @Test void createEndpointReturnsSuccessEnvelope() {
        var controller = new UserController(new IdentityApplicationService(new InMemoryUserRepository()));
        var response = controller.create(new CreateUserCommand("engineer1", "pwd"));
        assertTrue(response.success());
        assertEquals("engineer1", response.data().username());
    }
}
