/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SessionRepositoryPort
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.auth
 *
 * @Description : Application port for identity session persistence.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.auth;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.UserSession;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.value.SessionId;
import java.util.Optional;

public interface SessionRepositoryPort {

    UserSession save(UserSession session);

    Optional<UserSession> findById(SessionId sessionId);
}
