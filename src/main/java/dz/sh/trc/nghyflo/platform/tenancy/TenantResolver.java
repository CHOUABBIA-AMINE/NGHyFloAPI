package dz.sh.trc.nghyflo.platform.tenancy;

import dz.sh.trc.nghyflo.shared.api.ApiHeaders;
import jakarta.servlet.http.HttpServletRequest;

public class TenantResolver {
    public String resolve(HttpServletRequest request) {
        String tenant = request.getHeader(ApiHeaders.TENANT_ID);
        return (tenant == null || tenant.isBlank()) ? "default" : tenant;
    }
}
