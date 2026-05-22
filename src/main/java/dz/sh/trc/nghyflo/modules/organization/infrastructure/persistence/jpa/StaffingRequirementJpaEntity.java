package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ng_org_staffing_requirement", schema = "nghyflo")
public class StaffingRequirementJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "position_id", nullable = false, length = 64)
    private String positionId;

    @Column(name = "shift_id", nullable = false, length = 64)
    private String shiftId;

    @Column(name = "required_count", nullable = false)
    private int requiredCount;

    protected StaffingRequirementJpaEntity() {
    }

    public StaffingRequirementJpaEntity(String id, String positionId, String shiftId, int requiredCount) {
        this.id = id;
        this.positionId = positionId;
        this.shiftId = shiftId;
        this.requiredCount = requiredCount;
    }

    public String id() { return id; }
    public String positionId() { return positionId; }
    public String shiftId() { return shiftId; }
    public int requiredCount() { return requiredCount; }
}
