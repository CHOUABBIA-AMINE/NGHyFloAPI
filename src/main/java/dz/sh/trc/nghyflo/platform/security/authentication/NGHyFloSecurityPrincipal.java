/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloSecurityPrincipal
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.security.authentication
 *
 * @Description : Authenticated principal carrying trusted actor identity extracted from security credentials.
 *
 */
package dz.sh.trc.nghyflo.platform.security.authentication;

import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;

public record NGHyFloSecurityPrincipal(
        UserId userId,
        EmployeeId employeeId,
        TenantId tenantId,
        String username,
        boolean serviceAccount
) {

    public NGHyFloSecurityPrincipal {
        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }
        if (tenantId == null) {
            throw new IllegalArgumentException("tenantId is required");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username is required");
        }
    }
}
