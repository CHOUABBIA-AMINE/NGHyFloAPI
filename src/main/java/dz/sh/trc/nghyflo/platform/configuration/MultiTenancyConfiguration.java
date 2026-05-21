package dz.sh.trc.nghyflo.platform.configuration;

import dz.sh.trc.nghyflo.platform.tenancy.TenantFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiTenancyConfiguration {
    @Bean
    FilterRegistrationBean<TenantFilter> tenantFilter() {
        FilterRegistrationBean<TenantFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new TenantFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(2);
        return bean;
    }
}
