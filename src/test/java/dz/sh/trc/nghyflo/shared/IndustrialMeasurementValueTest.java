/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : IndustrialMeasurementValueTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared
 *
 * @Description : Verifies shared industrial measurement value object validation.
 *
 */
package dz.sh.trc.nghyflo.shared;

import dz.sh.trc.nghyflo.shared.domain.value.Density;
import dz.sh.trc.nghyflo.shared.domain.value.FlowRate;
import dz.sh.trc.nghyflo.shared.domain.value.Pressure;
import dz.sh.trc.nghyflo.shared.domain.value.QualityCode;
import dz.sh.trc.nghyflo.shared.domain.value.Temperature;
import dz.sh.trc.nghyflo.shared.domain.value.TimestampRange;
import dz.sh.trc.nghyflo.shared.domain.value.UnitOfMeasure;
import dz.sh.trc.nghyflo.shared.domain.value.Volume;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IndustrialMeasurementValueTest {

    @Test
    void shouldCreatePressureUsingBigDecimalAndNormalizeUnit() {
        Pressure pressure = Pressure.of(BigDecimal.valueOf(42.5), "bar");
        assertEquals(BigDecimal.valueOf(42.5), pressure.value());
        assertEquals("BAR", pressure.unit().code());
    }

    @Test
    void shouldRejectNegativeIndustrialMeasurements() {
        assertThrows(IllegalArgumentException.class, () -> Pressure.bar(BigDecimal.valueOf(-1)));
        assertThrows(IllegalArgumentException.class, () -> FlowRate.of(BigDecimal.valueOf(-1), "M3_H"));
        assertThrows(IllegalArgumentException.class, () -> Volume.of(BigDecimal.valueOf(-1), "M3"));
        assertThrows(IllegalArgumentException.class, () -> Density.of(BigDecimal.valueOf(-1), "KG_M3"));
    }

    @Test
    void shouldAllowTemperatureBelowZero() {
        Temperature temperature = Temperature.celsius(BigDecimal.valueOf(-5));
        assertEquals(BigDecimal.valueOf(-5), temperature.value());
    }

    @Test
    void shouldRejectBlankUnitAndQualityCode() {
        assertThrows(IllegalArgumentException.class, () -> UnitOfMeasure.of(" "));
        assertThrows(IllegalArgumentException.class, () -> QualityCode.of(" "));
    }

    @Test
    void shouldValidateTimestampRangeOrder() {
        Instant start = Instant.parse("2026-05-22T00:00:00Z");
        Instant end = Instant.parse("2026-05-22T01:00:00Z");
        TimestampRange range = TimestampRange.of(start, end);
        assertEquals(3600, range.duration().toSeconds());
        assertThrows(IllegalArgumentException.class, () -> TimestampRange.of(end, start));
    }
}
