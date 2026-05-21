/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EmployeeId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for an operational employee.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record EmployeeId(String value) {

    public EmployeeId {
        value = IdentifierValues.requireUuid(value, "EmployeeId");
    }

    public static EmployeeId newId() {
        return new EmployeeId(IdentifierValues.newUuid());
    }

    public static EmployeeId of(String value) {
        return new EmployeeId(value);
    }
}
