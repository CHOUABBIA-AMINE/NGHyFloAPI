/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : BootstrapStatusController
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Controller
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.bootstrap.api
 *
 * @Description : Exposes platform bootstrap health status
 *
 */
package dz.sh.trc.nghyflo.bootstrap.api;
import dz.sh.trc.nghyflo.bootstrap.NGHyFloApplicationIdentity;import org.springframework.core.env.Environment;import org.springframework.web.bind.annotation.*;import java.time.Instant;import java.util.Arrays;
@RestController @RequestMapping(NGHyFloApplicationIdentity.API_BASE_PATH+"/bootstrap") public class BootstrapStatusController {private final Environment env; public BootstrapStatusController(Environment env){this.env=env;} @GetMapping("/status") public BootstrapStatusResponse status(){return new BootstrapStatusResponse(NGHyFloApplicationIdentity.SYSTEM_CODE,NGHyFloApplicationIdentity.SYSTEM_NAME,NGHyFloApplicationIdentity.FULL_NAME,NGHyFloApplicationIdentity.API_BASE_PATH,"UP",Instant.now(),Arrays.asList(env.getActiveProfiles()));}}
