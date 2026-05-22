/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ProblemDetailsMapperTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.exception
 *
 * @Description : Verifies platform exception mapping into standard API error responses.
 *
 */
package dz.sh.trc.nghyflo.platform.exception;

import dz.sh.trc.nghyflo.shared.api.ErrorResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProblemDetailsMapperTest {

    private final ProblemDetailsMapper mapper = new ProblemDetailsMapper();

    @Test
    void shouldMapIllegalArgumentToBadRequest() {
        ErrorResponse error = mapper.fromIllegalArgument(new IllegalArgumentException("invalid input"), "/test");

        assertEquals("NGHYFLO_BAD_REQUEST", error.code());
        assertEquals(400, error.status());
        assertEquals("/test", error.path());
    }

    @Test
    void shouldMapIllegalStateToConflict() {
        ErrorResponse error = mapper.fromIllegalState(new IllegalStateException("invalid state"), "/state");

        assertEquals("NGHYFLO_CONFLICT", error.code());
        assertEquals(409, error.status());
    }

    @Test
    void shouldMapUnexpectedExceptionToInternalError() {
        ErrorResponse error = mapper.fromUnexpected(new RuntimeException("hidden"), "/boom");

        assertEquals("NGHYFLO_INTERNAL_ERROR", error.code());
        assertEquals(500, error.status());
        assertEquals("Unexpected NGHyFlo platform error", error.message());
    }
}
