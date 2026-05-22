package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ng_org_coverage_ref", schema = "nghyflo")
public class StaffingCoverageRefJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "owner_type", nullable = false, length = 64)
    private String ownerType;

    @Column(name = "owner_id", nullable = false, length = 64)
    private String ownerId;

    @Column(name = "reference_type", nullable = false, length = 64)
    private String referenceType;

    @Column(name = "reference_id", nullable = false, length = 64)
    private String referenceId;

    protected StaffingCoverageRefJpaEntity() {
    }

    public StaffingCoverageRefJpaEntity(
            String id,
            String ownerType,
            String ownerId,
            String referenceType,
            String referenceId
    ) {
        this.id = id;
        this.ownerType = ownerType;
        this.ownerId = ownerId;
        this.referenceType = referenceType;
        this.referenceId = referenceId;
    }

    public String id() { return id; }
    public String ownerType() { return ownerType; }
    public String ownerId() { return ownerId; }
    public String referenceType() { return referenceType; }
    public String referenceId() { return referenceId; }
}
