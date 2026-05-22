package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ng_org_position", schema = "nghyflo")
public class PositionJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "position_code", nullable = false, length = 128)
    private String positionCode;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @Column(name = "organization_unit_id", nullable = false, length = 64)
    private String organizationUnitId;

    @Column(name = "active", nullable = false)
    private boolean active;

    protected PositionJpaEntity() {
    }

    public PositionJpaEntity(String id, String positionCode, String title, String organizationUnitId, boolean active) {
        this.id = id;
        this.positionCode = positionCode;
        this.title = title;
        this.organizationUnitId = organizationUnitId;
        this.active = active;
    }

    public String id() {
        return id;
    }

    public String positionCode() {
        return positionCode;
    }

    public String title() {
        return title;
    }

    public String organizationUnitId() {
        return organizationUnitId;
    }

    public boolean active() {
        return active;
    }
}
