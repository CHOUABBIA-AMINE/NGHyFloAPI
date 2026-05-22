package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "ng_org_shift", schema = "nghyflo")
public class ShiftJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "shift_code", nullable = false, length = 128)
    private String shiftCode;

    @Column(name = "starts_at", nullable = false)
    private Instant startsAt;

    @Column(name = "ends_at", nullable = false)
    private Instant endsAt;

    protected ShiftJpaEntity() {
    }

    public ShiftJpaEntity(String id, String shiftCode, Instant startsAt, Instant endsAt) {
        this.id = id;
        this.shiftCode = shiftCode;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public String id() {
        return id;
    }

    public String shiftCode() {
        return shiftCode;
    }

    public Instant startsAt() {
        return startsAt;
    }

    public Instant endsAt() {
        return endsAt;
    }
}
