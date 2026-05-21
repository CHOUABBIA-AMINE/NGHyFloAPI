package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import dz.sh.trc.nghyflo.shared.domain.value.StructureId;

public class Employee {
    private final EmployeeId id;
    private final String fullName;
    private StructureId structureId;

    public Employee(EmployeeId id, String fullName) {
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("Employee name is required");
        this.id = id;
        this.fullName = fullName;
    }

    public void assignStructure(StructureId structureId) { this.structureId = structureId; }
    public EmployeeId id() { return id; }
    public String fullName() { return fullName; }
    public StructureId structureId() { return structureId; }
}
