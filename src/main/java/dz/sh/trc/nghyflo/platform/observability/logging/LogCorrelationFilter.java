package dz.sh.trc.nghyflo.platform.observability.logging;

import dz.sh.trc.nghyflo.shared.api.ApiHeaders;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

public class LogCorrelationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String cid = request.getHeader(ApiHeaders.CORRELATION_ID);
        if (cid == null || cid.isBlank()) {
            cid = CorrelationId.newId().value();
        }
        response.setHeader(ApiHeaders.CORRELATION_ID, cid);
        filterChain.doFilter(request, response);
    }
}
