/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CausationId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for event causation tracing.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record CausationId(String value) {

    public CausationId {
        value = IdentifierValues.requireUuid(value, "CausationId");
    }

    public static CausationId newId() {
        return new CausationId(IdentifierValues.newUuid());
    }

    public static CausationId of(String value) {
        return new CausationId(value);
    }
}
