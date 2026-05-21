package dz.sh.trc.nghyflo.shared;

import dz.sh.trc.nghyflo.shared.api.ApiResponse;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {
    @Test void createsSuccessEnvelope() {
        var r = ApiResponse.success("ok", CorrelationId.newId());
        assertTrue(r.success());
        assertNotNull(r.timestamp());
    }
}
