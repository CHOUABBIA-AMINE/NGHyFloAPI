/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CorrelationId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for request and process correlation.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record CorrelationId(String value) {

    public CorrelationId {
        value = IdentifierValues.requireUuid(value, "CorrelationId");
    }

    public static CorrelationId newId() {
        return new CorrelationId(IdentifierValues.newUuid());
    }

    public static CorrelationId of(String value) {
        return new CorrelationId(value);
    }
}
