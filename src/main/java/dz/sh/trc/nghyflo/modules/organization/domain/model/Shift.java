/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Shift
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Operational work shift for pipeline staffing.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.ShiftId;
import java.time.Instant;

public class Shift {
    private final ShiftId id;
    private final String code;
    private final Instant startsAt;
    private final Instant endsAt;

    public Shift(ShiftId id, String code, Instant startsAt, Instant endsAt) {
        if (id == null) {
            throw new IllegalArgumentException("shift id is required");
        }
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("shift code is required");
        }
        if (startsAt == null || endsAt == null || !endsAt.isAfter(startsAt)) {
            throw new IllegalArgumentException("shift time window is invalid");
        }
        this.id = id;
        this.code = code.trim().toUpperCase();
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public boolean activeAt(Instant instant) {
        return instant != null && !instant.isBefore(startsAt) && instant.isBefore(endsAt);
    }

    public ShiftId id() {
        return id;
    }

    public String code() {
        return code;
    }

    public Instant startsAt() {
        return startsAt;
    }

    public Instant endsAt() {
        return endsAt;
    }
}
