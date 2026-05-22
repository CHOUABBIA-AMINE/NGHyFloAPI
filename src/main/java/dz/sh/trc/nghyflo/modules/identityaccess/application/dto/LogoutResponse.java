/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : LogoutResponse
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.dto
 *
 * @Description : Response returned after session termination.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.dto;

public record LogoutResponse(String sessionId, boolean terminated) {
}
