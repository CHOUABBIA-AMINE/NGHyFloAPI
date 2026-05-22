/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SpringSecurityPasswordEncoderAdapterTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.security
 *
 * @Description : Verifies Spring Security credential encoding adapter behavior.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.security;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PasswordHash;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpringSecurityPasswordEncoderAdapterTest {

    private final SpringSecurityPasswordEncoderAdapter adapter = new SpringSecurityPasswordEncoderAdapter();

    @Test
    void shouldEncodeCredentialWithDelegatingEncoderPrefix() {
        String rawCredential = "credential-value-123";

        PasswordHash encoded = adapter.encode(rawCredential);

        assertTrue(encoded.value().startsWith("{bcrypt}"));
        assertNotEquals(rawCredential, encoded.value());
        assertTrue(adapter.matches(rawCredential, encoded));
        assertFalse(adapter.matches("different-credential", encoded));
    }

    @Test
    void shouldRejectBlankCredential() {
        assertThrows(IllegalArgumentException.class, () -> adapter.encode(" "));
    }
}
