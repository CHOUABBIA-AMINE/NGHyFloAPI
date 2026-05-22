package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "ng_org_shift_coverage_plan", schema = "nghyflo")
public class ShiftCoveragePlanJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "employee_id", nullable = false, length = 64)
    private String employeeId;

    @Column(name = "position_id", nullable = false, length = 64)
    private String positionId;

    @Column(name = "shift_id", nullable = false, length = 64)
    private String shiftId;

    @Column(name = "status", nullable = false, length = 64)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected ShiftCoveragePlanJpaEntity() {
    }

    public ShiftCoveragePlanJpaEntity(
            String id,
            String employeeId,
            String positionId,
            String shiftId,
            String status,
            Instant createdAt
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.positionId = positionId;
        this.shiftId = shiftId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String id() { return id; }
    public String employeeId() { return employeeId; }
    public String positionId() { return positionId; }
    public String shiftId() { return shiftId; }
    public String status() { return status; }
    public Instant createdAt() { return createdAt; }
}
