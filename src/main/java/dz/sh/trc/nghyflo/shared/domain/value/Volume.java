/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Volume
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Hydrocarbon volume measurement with validated engineering unit.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.math.BigDecimal;

public record Volume(BigDecimal value, UnitOfMeasure unit) {

    public Volume {
        value = MeasurementValues.requireNonNegative(value, "Volume");
        if (unit == null) {
            throw new IllegalArgumentException("Volume unit is required");
        }
    }

    public Volume(double value, String unit) {
        this(BigDecimal.valueOf(value), UnitOfMeasure.of(unit));
    }

    public static Volume of(BigDecimal value, String unit) {
        return new Volume(value, UnitOfMeasure.of(unit));
    }
}
