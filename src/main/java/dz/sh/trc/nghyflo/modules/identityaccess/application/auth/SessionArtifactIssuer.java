/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SessionArtifactIssuer
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.auth
 *
 * @Description : Application port for issuing access and renewal session artifacts.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.auth;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.User;
import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.UserSession;
import java.time.Instant;

public interface SessionArtifactIssuer {

    String issueAccessArtifact(User user, UserSession session, Instant expiresAt);

    String issueRenewalArtifact(User user, UserSession session, Instant expiresAt);

    String hashRenewalArtifact(String renewalArtifact);
}
