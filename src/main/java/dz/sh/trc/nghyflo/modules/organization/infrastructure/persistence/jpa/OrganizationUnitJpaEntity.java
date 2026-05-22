package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ng_org_unit", schema = "nghyflo")
public class OrganizationUnitJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "unit_code", nullable = false, length = 128)
    private String unitCode;

    @Column(name = "unit_name", nullable = false, length = 256)
    private String unitName;

    @Column(name = "parent_id", length = 64)
    private String parentId;

    @Column(name = "structure_id", length = 64)
    private String structureId;

    @Column(name = "active", nullable = false)
    private boolean active;

    protected OrganizationUnitJpaEntity() {
    }

    public OrganizationUnitJpaEntity(
            String id,
            String unitCode,
            String unitName,
            String parentId,
            String structureId,
            boolean active
    ) {
        this.id = id;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.parentId = parentId;
        this.structureId = structureId;
        this.active = active;
    }

    public String id() {
        return id;
    }

    public String unitCode() {
        return unitCode;
    }

    public String unitName() {
        return unitName;
    }

    public String parentId() {
        return parentId;
    }

    public String structureId() {
        return structureId;
    }

    public boolean active() {
        return active;
    }
}
