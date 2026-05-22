/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CorrelationContextTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.observability.correlation
 *
 * @Description : Verifies correlation identifier creation, storage, and clearing behavior.
 *
 */
package dz.sh.trc.nghyflo.platform.observability.correlation;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CorrelationContextTest {

    @AfterEach
    void clear() {
        CorrelationContext.clear();
    }

    @Test
    void shouldCreateCorrelationIdWhenMissing() {
        assertNotNull(CorrelationContext.getOrCreate());
    }

    @Test
    void shouldReturnConfiguredCorrelationId() {
        CorrelationId correlationId = CorrelationId.newId();

        CorrelationContext.set(correlationId);

        assertEquals(correlationId, CorrelationContext.getOrCreate());
    }

    @Test
    void shouldRejectNullCorrelationId() {
        assertThrows(IllegalArgumentException.class, () -> CorrelationContext.set(null));
    }
}
