/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : BootstrapStatusResponse
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Record
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.bootstrap.api
 *
 * @Description : Bootstrap status payload
 *
 */
package dz.sh.trc.nghyflo.bootstrap.api; import java.time.Instant; import java.util.List; public record BootstrapStatusResponse(String systemCode,String systemName,String fullName,String apiBasePath,String status,Instant timestamp,List<String> activeProfiles){}