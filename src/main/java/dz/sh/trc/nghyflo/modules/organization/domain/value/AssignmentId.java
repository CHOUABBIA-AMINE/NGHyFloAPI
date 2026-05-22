/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AssignmentId
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : UUID-backed staffing assignment identifier.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

import java.util.UUID;

public record AssignmentId(String value) {

    public AssignmentId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("assignmentId is required");
        }
        UUID.fromString(value);
    }

    public static AssignmentId newId() {
        return new AssignmentId(UUID.randomUUID().toString());
    }

    public static AssignmentId of(String value) {
        return new AssignmentId(value);
    }
}
