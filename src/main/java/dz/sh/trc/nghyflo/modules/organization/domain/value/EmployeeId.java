/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EmployeeId
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : UUID-backed organization employee identifier.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

import java.util.UUID;

public record EmployeeId(String value) {

    public EmployeeId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("employeeId is required");
        }
        UUID.fromString(value);
    }

    public static EmployeeId newId() {
        return new EmployeeId(UUID.randomUUID().toString());
    }

    public static EmployeeId of(String value) {
        return new EmployeeId(value);
    }
}
