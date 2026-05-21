/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : MeasurementPointId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for an operational measurement point.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record MeasurementPointId(String value) {

    public MeasurementPointId {
        value = IdentifierValues.requireUuid(value, "MeasurementPointId");
    }

    public static MeasurementPointId newId() {
        return new MeasurementPointId(IdentifierValues.newUuid());
    }

    public static MeasurementPointId of(String value) {
        return new MeasurementPointId(value);
    }
}
