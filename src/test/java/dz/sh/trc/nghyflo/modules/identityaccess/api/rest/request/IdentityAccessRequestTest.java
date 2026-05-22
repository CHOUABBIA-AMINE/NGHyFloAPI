/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : IdentityAccessRequestTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.api.rest.request
 *
 * @Description : Verifies identity-access REST request DTO validation.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.api.rest.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdentityAccessRequestTest {

    @Test
    void shouldCreateUserRequest() {
        CreateUserRequest request = new CreateUserRequest("operator.one", "secret-value");

        assertEquals("operator.one", request.username());
        assertEquals("secret-value", request.credential());
    }

    @Test
    void shouldRejectInvalidCreateUserRequest() {
        assertThrows(IllegalArgumentException.class, () -> new CreateUserRequest(" ", "secret-value"));
        assertThrows(IllegalArgumentException.class, () -> new CreateUserRequest("operator.one", " "));
    }

    @Test
    void shouldValidateAssignRoleRequest() {
        assertEquals("ROLE_OPERATOR", new AssignRoleRequest("ROLE_OPERATOR").roleCode());
        assertThrows(IllegalArgumentException.class, () -> new AssignRoleRequest(" "));
    }
}
