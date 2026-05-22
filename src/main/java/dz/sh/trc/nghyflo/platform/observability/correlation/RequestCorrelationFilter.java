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
    private static final String HEADER_NAME = "X-Correlation-Id";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String incoming = request.getHeader(HEADER_NAME);
        CorrelationId correlationId = incoming == null || incoming.isBlank()
                ? CorrelationId.newId()
                : CorrelationId.of(incoming);
        CorrelationContext.set(correlationId);
        response.setHeader(HEADER_NAME, correlationId.value());
        try {
            filterChain.doFilter(request, response);
        } finally {
            CorrelationContext.clear();
        }
    }
}
