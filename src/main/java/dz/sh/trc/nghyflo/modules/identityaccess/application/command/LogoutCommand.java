/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : LogoutCommand
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.command
 *
 * @Description : Application command requesting session logout.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.command;

public record LogoutCommand(String sessionId) {

    public LogoutCommand {
        if (sessionId == null || sessionId.isBlank()) {
            throw new IllegalArgumentException("sessionId is required");
        }
    }
}
