package dz.sh.trc.nghyflo.platform.tenancy;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

public class TenantFilter extends OncePerRequestFilter {
    private final TenantResolver tenantResolver = new TenantResolver();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            TenantContext.setTenant(tenantResolver.resolve(request));
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}
