/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ErrorResponse
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : API
 * @Package     : dz.sh.trc.nghyflo.shared.api
 *
 * @Description : Standard API error payload carrying code, message, status, and path details.
 *
 */
package dz.sh.trc.nghyflo.shared.api;

import java.time.Instant;

public record ErrorResponse(
        String code,
        String message,
        int status,
        String path,
        Instant timestamp
) {

    public ErrorResponse {
        if (code == null || code.isBlank()) {
            code = "NGHYFLO_ERROR";
        }
        if (message == null || message.isBlank()) {
            message = "Unexpected NGHyFlo API error";
        }
        if (status <= 0) {
            status = 500;
        }
        if (timestamp == null) {
            timestamp = Instant.now();
        }
    }

    public ErrorResponse(String message) {
        this("NGHYFLO_ERROR", message, 500, null, Instant.now());
    }

    public static ErrorResponse of(String code, String message, int status, String path) {
        return new ErrorResponse(code, message, status, path, Instant.now());
    }
}
