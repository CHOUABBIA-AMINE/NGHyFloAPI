/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Pressure
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Pressure measurement with validated industrial engineering unit.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.math.BigDecimal;

public record Pressure(BigDecimal value, UnitOfMeasure unit) {

    public Pressure {
        value = MeasurementValues.requireNonNegative(value, "Pressure");
        if (unit == null) {
            throw new IllegalArgumentException("Pressure unit is required");
        }
    }

    public Pressure(double value, String unit) {
        this(BigDecimal.valueOf(value), UnitOfMeasure.of(unit));
    }

    public static Pressure of(BigDecimal value, String unit) {
        return new Pressure(value, UnitOfMeasure.of(unit));
    }

    public static Pressure bar(BigDecimal value) {
        return new Pressure(value, UnitOfMeasure.of("BAR"));
    }

    public static Pressure pascal(BigDecimal value) {
        return new Pressure(value, UnitOfMeasure.of("PA"));
    }
}
