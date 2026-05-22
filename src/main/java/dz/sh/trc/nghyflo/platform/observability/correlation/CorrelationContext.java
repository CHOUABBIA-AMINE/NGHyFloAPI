/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CorrelationContext
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.observability.correlation
 *
 * @Description : Holds the current request correlation identifier for API error and response handling.
 *
 */
package dz.sh.trc.nghyflo.platform.observability.correlation;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;

public final class CorrelationContext {
    private static CorrelationId current;

    private CorrelationContext() {
    }

    public static void set(CorrelationId correlationId) {
        if (correlationId == null) {
            throw new IllegalArgumentException("correlationId is required");
        }
        current = correlationId;
    }

    public static CorrelationId getOrCreate() {
        if (current == null) {
            current = CorrelationId.newId();
        }
        return current;
    }

    public static void clear() {
        current = null;
    }
}
