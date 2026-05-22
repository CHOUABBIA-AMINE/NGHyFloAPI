/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : GlobalExceptionHandler
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : ControllerAdvice
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.exception
 *
 * @Description : Converts platform exceptions into standard NGHyFlo API response envelopes.
 *
 */
package dz.sh.trc.nghyflo.platform.exception;

import dz.sh.trc.nghyflo.platform.observability.correlation.CorrelationContext;
import dz.sh.trc.nghyflo.shared.api.ApiResponse;
import dz.sh.trc.nghyflo.shared.api.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ProblemDetailsMapper mapper = new ProblemDetailsMapper();

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(
            IllegalArgumentException exception,
            HttpServletRequest request
    ) {
        return response(mapper.fromIllegalArgument(exception, request.getRequestURI()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalState(
            IllegalStateException exception,
            HttpServletRequest request
    ) {
        return response(mapper.fromIllegalState(exception, request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpected(Exception exception, HttpServletRequest request) {
        return response(mapper.fromUnexpected(exception, request.getRequestURI()));
    }

    private ResponseEntity<ApiResponse<Void>> response(ErrorResponse error) {
        ApiResponse<Void> body = ApiResponse.failure(error, CorrelationContext.getOrCreate());
        return ResponseEntity.status(mapper.statusOf(error)).body(body);
    }
}
