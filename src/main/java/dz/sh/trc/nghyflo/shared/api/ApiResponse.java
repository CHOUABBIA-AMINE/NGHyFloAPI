/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ApiResponse
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Record
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.shared.api
 *
 * @Description : Standard API envelope used for deterministic correlation and error handling.
 *
 */
package dz.sh.trc.nghyflo.shared.api;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import java.time.Instant;

public record ApiResponse<T>(boolean success, T data, ErrorResponse error, Instant timestamp, CorrelationId correlationId) {
    public ApiResponse {
        if (timestamp == null) {
            timestamp = Instant.now();
        }
    }
    public static <T> ApiResponse<T> success(T data, CorrelationId correlationId) {
        return new ApiResponse<>(true, data, null, Instant.now(), correlationId);
    }
    public static <T> ApiResponse<T> failure(ErrorResponse error, CorrelationId correlationId) {
        return new ApiResponse<>(false, null, error, Instant.now(), correlationId);
    }
}
