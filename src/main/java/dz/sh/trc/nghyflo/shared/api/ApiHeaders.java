/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ApiHeaders
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.shared.api
 *
 * @Description : Standard API headers used for correlation and tenant context propagation.
 *
 */
package dz.sh.trc.nghyflo.shared.api;

public final class ApiHeaders {
    public static final String CORRELATION_ID = "X-Correlation-Id";
    public static final String TENANT_ID = "X-Tenant-Id";
    private ApiHeaders() {
    }
}
