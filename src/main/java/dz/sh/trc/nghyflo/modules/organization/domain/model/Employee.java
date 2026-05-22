package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;

public class Employee {
    private final EmployeeId id;
    private final String fullName;
    private StructureId structureId;
    private EmployeeStatus status;

    public Employee(EmployeeId id, String fullName) {
        if (id == null) {
            throw new IllegalArgumentException("Employee id is required");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Employee name is required");
        }
        this.id = id;
        this.fullName = fullName.trim();
        this.status = EmployeeStatus.ACTIVE;
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

    public StructureId structureId() {
        return structureId;
    }

    public EmployeeStatus status() {
        return status;
    }
}
