package dz.sh.trc.nghyflo.platform;

import dz.sh.trc.nghyflo.platform.observability.logging.LogCorrelationFilter;
import dz.sh.trc.nghyflo.shared.api.ApiHeaders;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;

class CorrelationFilterTest {
    @Test void addsCorrelationHeader() throws ServletException, IOException {
        var filter = new LogCorrelationFilter();
        var req = new MockHttpServletRequest();
        var res = new MockHttpServletResponse();
        filter.doFilter(req,res,(a,b)->{});
        assertNotNull(res.getHeader(ApiHeaders.CORRELATION_ID));
    }
}
