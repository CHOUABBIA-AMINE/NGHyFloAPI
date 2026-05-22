/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationValueObjectTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.value
 *
 * @Description : Verifies organization value object validation and normalization.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.value;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrganizationValueObjectTest {

    @Test
    void shouldCreateUuidBackedIdentifiers() {
        assertNotNull(OrganizationUnitId.newId());
        assertNotNull(PositionId.newId());
        assertNotNull(CoverageAllocationId.newId());
        assertNotNull(CoverageAllocationId.newId().asAssignmentId());
    }

    @Test
    void shouldRejectInvalidIdentifiers() {
        assertThrows(IllegalArgumentException.class, () -> OrganizationUnitId.of("invalid"));
        assertThrows(IllegalArgumentException.class, () -> PositionId.of("invalid"));
        assertThrows(IllegalArgumentException.class, () -> CoverageAllocationId.of("invalid"));
    }

    @Test
    void shouldNormalizeCodesAndNumbers() {
        assertEquals("TRC-OPS", OrganizationUnitCode.of(" trc-ops ").value());
        assertEquals("SUPERVISOR", PositionCode.of(" supervisor ").value());
        assertEquals("E-001", EmployeeNumber.of(" e-001 ").value());
        assertEquals("DAY", ShiftCode.of(" day ").value());
    }

    @Test
    void shouldRejectBlankCodesAndNumbers() {
        assertThrows(IllegalArgumentException.class, () -> OrganizationUnitCode.of(" "));
        assertThrows(IllegalArgumentException.class, () -> PositionCode.of(" "));
        assertThrows(IllegalArgumentException.class, () -> EmployeeNumber.of(" "));
        assertThrows(IllegalArgumentException.class, () -> ShiftCode.of(" "));
    }
}
