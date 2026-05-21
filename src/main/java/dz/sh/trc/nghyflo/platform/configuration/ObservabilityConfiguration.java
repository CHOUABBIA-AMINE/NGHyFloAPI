package dz.sh.trc.nghyflo.platform.configuration;

import dz.sh.trc.nghyflo.platform.observability.logging.LogCorrelationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservabilityConfiguration {
    @Bean
    FilterRegistrationBean<LogCorrelationFilter> correlationFilter() {
        FilterRegistrationBean<LogCorrelationFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogCorrelationFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }
}
