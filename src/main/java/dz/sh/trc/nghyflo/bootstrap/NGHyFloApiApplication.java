/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NGHyFloApiApplication
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Bootstrap
 * @Package     : dz.sh.trc.nghyflo.bootstrap
 *
 * @Description : Spring Boot entrypoint
 *
 */
package dz.sh.trc.nghyflo.bootstrap; import org.springframework.boot.autoconfigure.SpringBootApplication; import org.springframework.boot.SpringApplication; @SpringBootApplication public class NGHyFloApiApplication { public static void main(String[] a){SpringApplication.run(NGHyFloApiApplication.class,a);} }