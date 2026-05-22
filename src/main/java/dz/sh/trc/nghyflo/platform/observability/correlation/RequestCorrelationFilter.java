/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : RequestCorrelationFilter
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Filter
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.observability.correlation
 *
 * @Description : Propagates a correlation identifier through each HTTP request and response.
 *
 */
package dz.sh.trc.nghyflo.platform.observability.correlation;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

public class RequestCorrelationFilter extends OncePerRequestFilter {
    static final String CORRELATION_HEADER = "X-Correlation-Id";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        CorrelationId correlationId = resolveCorrelationId(request);
        CorrelationContext.set(correlationId);
        response.setHeader(CORRELATION_HEADER, correlationId.value());
        try {
            filterChain.doFilter(request, response);
        } finally {
            CorrelationContext.clear();
        }
    }

    private CorrelationId resolveCorrelationId(HttpServletRequest request) {
        String incoming = request.getHeader(CORRELATION_HEADER);
        if (incoming == null || incoming.isBlank()) {
            return CorrelationId.newId();
        }
        return CorrelationId.of(incoming);
    }
}
