/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Density
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Hydrocarbon density measurement with validated engineering unit.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.math.BigDecimal;

public record Density(BigDecimal value, UnitOfMeasure unit) {

    public Density {
        value = MeasurementValues.requireNonNegative(value, "Density");
        if (unit == null) {
            throw new IllegalArgumentException("Density unit is required");
        }
    }

    public Density(double value, String unit) {
        this(BigDecimal.valueOf(value), UnitOfMeasure.of(unit));
    }

    public static Density of(BigDecimal value, String unit) {
        return new Density(value, UnitOfMeasure.of(unit));
    }
}
