package dz.sh.trc.nghyflo.platform;

import dz.sh.trc.nghyflo.platform.tenancy.TenantContext;
import dz.sh.trc.nghyflo.platform.tenancy.TenantFilter;
import dz.sh.trc.nghyflo.shared.api.ApiHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertNull;

class TenantFilterTest {
    @Test
    void clearsContextAfterChain() throws Exception {
        var f = new TenantFilter();
        var req = new MockHttpServletRequest();
        req.addHeader(ApiHeaders.TENANT_ID, "trc");
        f.doFilter(req, new MockHttpServletResponse(), (a, b) -> {});
        assertNull(TenantContext.getTenant());
    }
}
