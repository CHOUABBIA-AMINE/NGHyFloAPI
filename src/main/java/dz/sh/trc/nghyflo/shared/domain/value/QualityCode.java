/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : QualityCode
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Measurement quality code used to classify telemetry and operational readings.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record QualityCode(String value) {

    public static final QualityCode GOOD = new QualityCode("GOOD");
    public static final QualityCode SUSPECT = new QualityCode("SUSPECT");
    public static final QualityCode BAD = new QualityCode("BAD");
    public static final QualityCode ESTIMATED = new QualityCode("ESTIMATED");
    public static final QualityCode MISSING = new QualityCode("MISSING");

    public QualityCode {
        value = MeasurementValues.requireText(value, "QualityCode");
    }

    public static QualityCode of(String value) {
        return new QualityCode(value);
    }
}
