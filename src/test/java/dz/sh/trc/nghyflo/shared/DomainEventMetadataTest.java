package dz.sh.trc.nghyflo.shared;

import dz.sh.trc.nghyflo.shared.domain.event.EventMetadata;
import dz.sh.trc.nghyflo.shared.domain.value.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DomainEventMetadataTest {
    @Test void buildsValidMetadata() {
        EventMetadata m = new EventMetadata(UserId.newId(), TenantId.newId(), CorrelationId.newId(), CausationId.newId(), "measurement", 1);
        assertEquals("measurement", m.sourceModule());
    }
}
