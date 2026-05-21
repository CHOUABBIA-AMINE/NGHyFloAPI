package dz.sh.trc.nghyflo.platform;

import dz.sh.trc.nghyflo.platform.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    @Test void wrapsInApiResponse() {
        var r = new GlobalExceptionHandler().handle(new IllegalArgumentException("bad"));
        assertEquals(HttpStatus.BAD_REQUEST, r.getStatusCode());
        assertFalse(r.getBody().success());
    }
}
