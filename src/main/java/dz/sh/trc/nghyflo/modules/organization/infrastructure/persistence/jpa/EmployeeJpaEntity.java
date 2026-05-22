package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ng_org_employee", schema = "nghyflo")
public class EmployeeJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "full_name", nullable = false, length = 256)
    private String fullName;

    @Column(name = "structure_id", length = 64)
    private String structureId;

    @Column(name = "status", nullable = false, length = 64)
    private String status;

    protected EmployeeJpaEntity() {
    }

    public EmployeeJpaEntity(String id, String fullName, String structureId, String status) {
        this.id = id;
        this.fullName = fullName;
        this.structureId = structureId;
        this.status = status;
    }

    public String id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public String structureId() {
        return structureId;
    }

    public String status() {
        return status;
    }
}
