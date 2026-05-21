/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SensorId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for a telemetry sensor.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record SensorId(String value) {

    public SensorId {
        value = IdentifierValues.requireUuid(value, "SensorId");
    }

    public static SensorId newId() {
        return new SensorId(IdentifierValues.newUuid());
    }

    public static SensorId of(String value) {
        return new SensorId(value);
    }
}
