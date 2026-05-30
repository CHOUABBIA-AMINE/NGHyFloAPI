/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : StructureId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for a structure.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

@Deprecated(since = "R02-C01")
public record StructureId(String value) {

    public StructureId {
        value = IdentifierValues.requireUuid(value, "StructureId");
    }

    public static StructureId newId() {
        return new StructureId(IdentifierValues.newUuid());
    }

    public static StructureId of(String value) {
        return new StructureId(value);
    }
}
