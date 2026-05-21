/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : MeasurementValues
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Central validation utility for industrial measurement value objects.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.math.BigDecimal;

final class MeasurementValues {

    private MeasurementValues() {
    }

    static BigDecimal requireValue(BigDecimal value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " value is required");
        }
        return value;
    }

    static BigDecimal requireNonNegative(BigDecimal value, String fieldName) {
        BigDecimal checked = requireValue(value, fieldName);
        if (checked.signum() < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return checked;
    }

    static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return value.trim().toUpperCase();
    }
}
