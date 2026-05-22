/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloApiApplication
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap
 *
 * @Description : Spring Boot entry point for the NGHyFlo backend platform.
 *
 */
package dz.sh.trc.nghyflo.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dz.sh.trc.nghyflo")
public class NGHyFloApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NGHyFloApiApplication.class, args);
    }
}
