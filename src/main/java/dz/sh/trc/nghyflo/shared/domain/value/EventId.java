/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EventId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for a domain or outbox event.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record EventId(String value) {

    public EventId {
        value = IdentifierValues.requireUuid(value, "EventId");
    }

    public static EventId newId() {
        return new EventId(IdentifierValues.newUuid());
    }

    public static EventId of(String value) {
        return new EventId(value);
    }
}
