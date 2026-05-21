/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ProblemDetailsMapper
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.exception
 *
 * @Description : Maps platform exceptions to unified NGHyFlo API error envelopes.
 *
 */
package dz.sh.trc.nghyflo.platform.exception;

import dz.sh.trc.nghyflo.shared.api.ErrorResponse;

public class ProblemDetailsMapper {
    public ErrorResponse map(String code, String message) {
        return new ErrorResponse(code + ": " + message);
    }
}
