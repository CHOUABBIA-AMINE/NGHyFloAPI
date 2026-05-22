/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : EmployeeNumber
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : Normalized operational employee number.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

public record EmployeeNumber(String value) {

    public EmployeeNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("employeeNumber is required");
        }
        value = value.trim().toUpperCase();
    }

    public static EmployeeNumber of(String value) {
        return new EmployeeNumber(value);
    }
}
