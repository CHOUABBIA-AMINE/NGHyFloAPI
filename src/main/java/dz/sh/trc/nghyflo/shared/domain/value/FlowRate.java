/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : FlowRate
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Hydrocarbon flow-rate measurement with validated engineering unit.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.math.BigDecimal;

public record FlowRate(BigDecimal value, UnitOfMeasure unit) {

    public FlowRate {
        value = MeasurementValues.requireNonNegative(value, "FlowRate");
        if (unit == null) {
            throw new IllegalArgumentException("FlowRate unit is required");
        }
    }

    public FlowRate(double value, String unit) {
        this(BigDecimal.valueOf(value), UnitOfMeasure.of(unit));
    }

    public static FlowRate of(BigDecimal value, String unit) {
        return new FlowRate(value, UnitOfMeasure.of(unit));
    }

    public static FlowRate cubicMetersPerHour(BigDecimal value) {
        return new FlowRate(value, UnitOfMeasure.of("M3_H"));
    }
}
