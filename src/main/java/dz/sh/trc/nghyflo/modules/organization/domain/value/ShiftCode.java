/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ShiftCode
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : Normalized operational shift code.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

public record ShiftCode(String value) {

    public ShiftCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("shiftCode is required");
        }
        value = value.trim().toUpperCase();
    }

    public static ShiftCode of(String value) {
        return new ShiftCode(value);
    }
}
