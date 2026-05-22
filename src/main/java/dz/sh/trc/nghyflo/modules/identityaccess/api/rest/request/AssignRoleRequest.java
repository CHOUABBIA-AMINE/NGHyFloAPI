/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AssignRoleRequest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.api.rest.request
 *
 * @Description : REST request payload for assigning a role to an NGHyFlo user.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.api.rest.request;

public record AssignRoleRequest(String roleCode) {

    public AssignRoleRequest {
        if (roleCode == null || roleCode.isBlank()) {
            throw new IllegalArgumentException("roleCode is required");
        }
    }
}
