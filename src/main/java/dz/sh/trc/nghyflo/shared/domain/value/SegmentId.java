/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SegmentId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for a pipeline segment.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record SegmentId(String value) {

    public SegmentId {
        value = IdentifierValues.requireUuid(value, "SegmentId");
    }

    public static SegmentId newId() {
        return new SegmentId(IdentifierValues.newUuid());
    }

    public static SegmentId of(String value) {
        return new SegmentId(value);
    }
}
