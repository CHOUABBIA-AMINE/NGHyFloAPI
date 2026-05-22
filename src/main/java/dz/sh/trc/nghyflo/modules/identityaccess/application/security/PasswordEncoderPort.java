/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : PasswordEncoderPort
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.security
 *
 * @Description : Application boundary for password hashing and matching without exposing infrastructure details.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.security;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.PasswordHash;

public interface PasswordEncoderPort {

    PasswordHash encode(String rawPassword);

    boolean matches(String rawPassword, PasswordHash passwordHash);
}
