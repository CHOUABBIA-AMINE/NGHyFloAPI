/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : RenewalRepositoryPort
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Interface
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.auth
 *
 * @Description : Application port for persisted renewal artifacts.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.auth;

import dz.sh.trc.nghyflo.modules.identityaccess.domain.model.RefreshToken;
import java.util.Optional;

public interface RenewalRepositoryPort {

    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByArtifact(String artifact);
}
