/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Temperature
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Temperature measurement with validated industrial engineering unit.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.math.BigDecimal;

public record Temperature(BigDecimal value, UnitOfMeasure unit) {

    public Temperature {
        value = MeasurementValues.requireValue(value, "Temperature");
        if (unit == null) {
            throw new IllegalArgumentException("Temperature unit is required");
        }
    }

    public Temperature(double value, String unit) {
        this(BigDecimal.valueOf(value), UnitOfMeasure.of(unit));
    }

    public static Temperature of(BigDecimal value, String unit) {
        return new Temperature(value, UnitOfMeasure.of(unit));
    }

    public static Temperature celsius(BigDecimal value) {
        return new Temperature(value, UnitOfMeasure.of("C"));
    }
}
