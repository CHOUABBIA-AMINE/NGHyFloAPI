/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CorrelationConfiguration
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Configuration
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.observability.correlation
 *
 * @Description : Registers request correlation infrastructure for API requests.
 *
 */
package dz.sh.trc.nghyflo.platform.observability.correlation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorrelationConfiguration {

    @Bean
    public RequestCorrelationFilter requestCorrelationFilter() {
        return new RequestCorrelationFilter();
    }
}
