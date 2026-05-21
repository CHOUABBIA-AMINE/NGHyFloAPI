package dz.sh.trc.nghyflo.platform.exception;

import dz.sh.trc.nghyflo.shared.api.ApiResponse;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ProblemDetailsMapper mapper = new ProblemDetailsMapper();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handle(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.failure(mapper.map("NGHYFLO-ERROR", ex.getMessage()), CorrelationId.newId()));
    }
}
