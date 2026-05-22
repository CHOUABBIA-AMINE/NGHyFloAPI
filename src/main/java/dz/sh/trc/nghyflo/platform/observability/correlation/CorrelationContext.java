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
    private static final ThreadLocal<CorrelationId> CURRENT = new ThreadLocal<>();

    private CorrelationContext() {
    }

    public static void set(CorrelationId correlationId) {
        if (correlationId == null) {
            throw new IllegalArgumentException("correlationId is required");
        }
        CURRENT.set(correlationId);
    }

    public static CorrelationId getOrCreate() {
        CorrelationId correlationId = CURRENT.get();
        if (correlationId == null) {
            correlationId = CorrelationId.newId();
            CURRENT.set(correlationId);
        }
        return correlationId;
    }

    public static void clear() {
        CURRENT.remove();
    }
}
