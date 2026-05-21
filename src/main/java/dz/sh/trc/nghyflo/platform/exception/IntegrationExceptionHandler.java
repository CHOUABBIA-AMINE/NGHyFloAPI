package dz.sh.trc.nghyflo.platform.exception;

import dz.sh.trc.nghyflo.shared.api.ApiResponse;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IntegrationExceptionHandler {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<Void>> handle(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(ApiResponse.failure(new ProblemDetailsMapper().map("NGHYFLO-INT-502", ex.getMessage()), CorrelationId.newId()));
    }
}
