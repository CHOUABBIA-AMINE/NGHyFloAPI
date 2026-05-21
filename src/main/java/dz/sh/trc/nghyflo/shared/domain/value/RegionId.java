/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : RegionId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for an operational region.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record RegionId(String value) {

    public RegionId {
        value = IdentifierValues.requireUuid(value, "RegionId");
    }

    public static RegionId newId() {
        return new RegionId(IdentifierValues.newUuid());
    }

    public static RegionId of(String value) {
        return new RegionId(value);
    }
}
