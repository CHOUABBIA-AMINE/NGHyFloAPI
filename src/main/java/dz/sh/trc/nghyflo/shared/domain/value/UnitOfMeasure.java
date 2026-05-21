/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : UnitOfMeasure
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Engineering unit descriptor used by NGHyFlo industrial measurements.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record UnitOfMeasure(String code) {

    public UnitOfMeasure {
        code = MeasurementValues.requireText(code, "UnitOfMeasure");
    }

    public static UnitOfMeasure of(String code) {
        return new UnitOfMeasure(code);
    }
}
