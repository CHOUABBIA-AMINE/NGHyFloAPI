/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : GatewayId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for an edge or telemetry gateway.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record GatewayId(String value) {

    public GatewayId {
        value = IdentifierValues.requireUuid(value, "GatewayId");
    }

    public static GatewayId newId() {
        return new GatewayId(IdentifierValues.newUuid());
    }

    public static GatewayId of(String value) {
        return new GatewayId(value);
    }
}
