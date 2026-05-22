/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationCoverageController
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Controller
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.modules.organization.api.rest
 *
 * @Description : Exposes organization coverage evaluation endpoints.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.api.rest;

import dz.sh.trc.nghyflo.modules.organization.api.rest.mapper.OrganizationApiMapper;
import dz.sh.trc.nghyflo.modules.organization.api.rest.request.EvaluateCoverageRequest;
import dz.sh.trc.nghyflo.modules.organization.api.rest.request.EvaluateOperationalScopeRequest;
import dz.sh.trc.nghyflo.modules.organization.application.dto.CoverageEvaluationResponse;
import dz.sh.trc.nghyflo.modules.organization.application.service.OrganizationCoverageService;
import dz.sh.trc.nghyflo.platform.observability.correlation.CorrelationContext;
import dz.sh.trc.nghyflo.shared.api.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nghyflo/api/v1/organization")
public class OrganizationCoverageController {
    private final OrganizationCoverageService service;
    private final OrganizationApiMapper mapper = new OrganizationApiMapper();

    public OrganizationCoverageController(OrganizationCoverageService service) {
        this.service = service;
    }

    @PostMapping("/coverage/evaluate")
    public ApiResponse<CoverageEvaluationResponse> evaluateCoverage(@RequestBody EvaluateCoverageRequest request) {
        CoverageEvaluationResponse response = service.evaluate(mapper.toCommand(request));
        return ApiResponse.success(response, CorrelationContext.getOrCreate());
    }

    @PostMapping("/operational-scope/evaluate")
    public ApiResponse<CoverageEvaluationResponse> evaluateOperationalScope(
            @RequestBody EvaluateOperationalScopeRequest request
    ) {
        CoverageEvaluationResponse response = service.evaluate(mapper.toCommand(request));
        return ApiResponse.success(response, CorrelationContext.getOrCreate());
    }
}
