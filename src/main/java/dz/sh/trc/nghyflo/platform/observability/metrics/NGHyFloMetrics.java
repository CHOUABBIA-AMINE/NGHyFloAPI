package dz.sh.trc.nghyflo.platform.observability.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class NGHyFloMetrics {
    private final Counter apiErrors;

    public NGHyFloMetrics(MeterRegistry registry) {
        this.apiErrors = Counter.builder("nghyflo.api.errors.total").register(registry);
    }

    public void incrementApiErrors() {
        apiErrors.increment();
    }
}
