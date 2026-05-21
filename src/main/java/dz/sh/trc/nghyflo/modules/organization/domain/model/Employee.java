/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Employee
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Represents a Sonatrach operational employee assigned to an NGHyFlo structure.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;

public class Employee {
    private final EmployeeId id;
    private final String fullName;
    private StructureId structureId;

    public Employee(EmployeeId id, String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Employee name is required");
        }
        this.id = id;
        this.fullName = fullName;
    }

    public void assignStructure(StructureId structureId) {
        this.structureId = structureId;
    }

    public EmployeeId id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public StructureId structureId() {
        return structureId;
    }
}
