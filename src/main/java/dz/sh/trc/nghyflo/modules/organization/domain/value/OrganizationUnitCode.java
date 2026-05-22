/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationUnitCode
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : Normalized organization unit code.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

public record OrganizationUnitCode(String value) {

    public OrganizationUnitCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("organizationUnitCode is required");
        }
        value = value.trim().toUpperCase();
    }

    public static OrganizationUnitCode of(String value) {
        return new OrganizationUnitCode(value);
    }
}
