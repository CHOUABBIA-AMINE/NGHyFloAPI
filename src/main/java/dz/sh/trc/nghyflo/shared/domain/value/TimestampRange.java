/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : TimestampRange
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Validated time window value object for operational queries and replay.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.time.Duration;
import java.time.Instant;

public record TimestampRange(Instant startInclusive, Instant endExclusive) {

    public TimestampRange {
        if (startInclusive == null) {
            throw new IllegalArgumentException("Range start is required");
        }
        if (endExclusive == null) {
            throw new IllegalArgumentException("Range end is required");
        }
        if (!endExclusive.isAfter(startInclusive)) {
            throw new IllegalArgumentException("Range end must be after range start");
        }
    }

    public static TimestampRange of(Instant startInclusive, Instant endExclusive) {
        return new TimestampRange(startInclusive, endExclusive);
    }

    public Duration duration() {
        return Duration.between(startInclusive, endExclusive);
    }
}
