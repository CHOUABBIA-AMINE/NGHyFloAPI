/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloApplicationRunner
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap
 *
 * @Description : Logs bootstrap readiness lifecycle at application startup.
 *
 */
package dz.sh.trc.nghyflo.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class NGHyFloApplicationRunner implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(NGHyFloApplicationRunner.class);

    @Override
    public void run(ApplicationArguments args) {
        LOGGER.info("{} bootstrap initialized with base path {}", NGHyFloApplicationIdentity.SYSTEM_NAME, NGHyFloApplicationIdentity.API_BASE_PATH);
    }
}
