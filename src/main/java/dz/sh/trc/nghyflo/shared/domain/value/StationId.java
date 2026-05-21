/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : StationId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for an operational station.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record StationId(String value) {

    public StationId {
        value = IdentifierValues.requireUuid(value, "StationId");
    }

    public static StationId newId() {
        return new StationId(IdentifierValues.newUuid());
    }

    public static StationId of(String value) {
        return new StationId(value);
    }
}
