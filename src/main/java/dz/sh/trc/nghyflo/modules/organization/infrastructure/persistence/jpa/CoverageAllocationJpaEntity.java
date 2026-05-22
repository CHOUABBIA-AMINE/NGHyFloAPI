package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "ng_org_coverage_allocation", schema = "nghyflo")
public class CoverageAllocationJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "employee_id", nullable = false, length = 64)
    private String employeeId;

    @Column(name = "shift_id", nullable = false, length = 64)
    private String shiftId;

    @Column(name = "region_ids", nullable = false)
    private String regionIds;

    @Column(name = "structure_ids", nullable = false)
    private String structureIds;

    @Column(name = "pipeline_ids", nullable = false)
    private String pipelineIds;

    @Column(name = "station_ids", nullable = false)
    private String stationIds;

    @Column(name = "status", nullable = false, length = 64)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected CoverageAllocationJpaEntity() {
    }

    public CoverageAllocationJpaEntity(
            String id,
            String employeeId,
            String shiftId,
            String regionIds,
            String structureIds,
            String pipelineIds,
            String stationIds,
            String status,
            Instant createdAt
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.shiftId = shiftId;
        this.regionIds = regionIds;
        this.structureIds = structureIds;
        this.pipelineIds = pipelineIds;
        this.stationIds = stationIds;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String id() { return id; }
    public String employeeId() { return employeeId; }
    public String shiftId() { return shiftId; }
    public String regionIds() { return regionIds; }
    public String structureIds() { return structureIds; }
    public String pipelineIds() { return pipelineIds; }
    public String stationIds() { return stationIds; }
    public String status() { return status; }
    public Instant createdAt() { return createdAt; }
}
