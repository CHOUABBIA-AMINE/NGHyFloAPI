/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ProblemDetailsMapper
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
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
import org.springframework.http.HttpStatus;

public class ProblemDetailsMapper {

    public ErrorResponse map(String code, String message) {
        return ErrorResponse.of(code, message, 500, null);
    }

    public ErrorResponse map(String code, String message, int status, String path) {
        return ErrorResponse.of(code, message, status, path);
    }

    public ErrorResponse fromIllegalArgument(IllegalArgumentException exception, String path) {
        return map("NGHYFLO_BAD_REQUEST", exception.getMessage(), 400, path);
    }

    public ErrorResponse fromIllegalState(IllegalStateException exception, String path) {
        return map("NGHYFLO_CONFLICT", exception.getMessage(), 409, path);
    }

    public ErrorResponse fromUnexpected(Exception exception, String path) {
        return map("NGHYFLO_INTERNAL_ERROR", "Unexpected NGHyFlo platform error", 500, path);
    }

    public HttpStatus statusOf(ErrorResponse error) {
        return HttpStatus.valueOf(error.status());
    }
}
