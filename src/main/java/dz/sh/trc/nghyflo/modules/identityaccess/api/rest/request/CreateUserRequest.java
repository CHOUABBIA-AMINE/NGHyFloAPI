/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CreateUserRequest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.api.rest.request
 *
 * @Description : REST request payload for creating an NGHyFlo identity-access user.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.api.rest.request;

public record CreateUserRequest(
        String username,
        String credential
) {

    public CreateUserRequest {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username is required");
        }
        if (credential == null || credential.isBlank()) {
            throw new IllegalArgumentException("credential is required");
        }
    }
}
