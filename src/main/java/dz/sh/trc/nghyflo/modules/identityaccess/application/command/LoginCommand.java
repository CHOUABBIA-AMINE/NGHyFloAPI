/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : LoginCommand
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Application
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.application.command
 *
 * @Description : Application command requesting authentication for an identity-access user.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.application.command;

public record LoginCommand(String username, String credential, String sourceAddress) {

    public LoginCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username is required");
        }
        if (credential == null || credential.isBlank()) {
            throw new IllegalArgumentException("credential is required");
        }
        sourceAddress = sourceAddress == null || sourceAddress.isBlank() ? "unknown" : sourceAddress.trim();
    }
}
