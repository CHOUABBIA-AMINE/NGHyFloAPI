/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : UserId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for an NGHyFlo user.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record UserId(String value) {

    public UserId {
        value = IdentifierValues.requireUuid(value, "UserId");
    }

    public static UserId newId() {
        return new UserId(IdentifierValues.newUuid());
    }

    public static UserId of(String value) {
        return new UserId(value);
    }
}
