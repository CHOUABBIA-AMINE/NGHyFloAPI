/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Employee
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Represents an operational employee assigned to an NGHyFlo organization structure.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.EmployeeNumber;
import dz.sh.trc.nghyflo.modules.organization.domain.value.StructureId;
import java.util.Optional;

public class Employee {
    private final EmployeeId id;
    private final String fullName;
    private EmployeeNumber employeeNumber;
    private StructureId structureId;
    private EmployeeStatus status;

    public Employee(EmployeeId id, String fullName) {
        this(id, fullName, null);
    }

    public Employee(EmployeeId id, String fullName, EmployeeNumber employeeNumber) {
        if (id == null) {
            throw new IllegalArgumentException("Employee id is required");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Employee name is required");
        }
        this.id = id;
        this.fullName = fullName.trim();
        this.employeeNumber = employeeNumber;
        this.status = EmployeeStatus.ACTIVE;
    }

    public void assignEmployeeNumber(EmployeeNumber employeeNumber) {
        if (employeeNumber == null) {
            throw new IllegalArgumentException("employeeNumber is required");
        }
        this.employeeNumber = employeeNumber;
    }

    public void assignStructure(StructureId structureId) {
        if (structureId == null) {
            throw new IllegalArgumentException("Structure id is required");
        }
        this.structureId = structureId;
    }

    public void suspend() {
        this.status = EmployeeStatus.SUSPENDED;
    }

    public void markOnLeave() {
        this.status = EmployeeStatus.ON_LEAVE;
    }

    public void retire() {
        this.status = EmployeeStatus.RETIRED;
    }

    public boolean activeInStructure() {
        return status == EmployeeStatus.ACTIVE && structureId != null;
    }

    public EmployeeId id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public Optional<EmployeeNumber> employeeNumber() {
        return Optional.ofNullable(employeeNumber);
    }

    public StructureId structureId() {
        return structureId;
    }

    public EmployeeStatus status() {
        return status;
    }
}
