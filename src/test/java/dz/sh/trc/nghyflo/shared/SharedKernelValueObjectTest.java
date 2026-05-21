package dz.sh.trc.nghyflo.shared;

import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SharedKernelValueObjectTest {
    @Test void rejectsInvalidUuid() { assertThrows(IllegalArgumentException.class, () -> new TenantId("abc")); }
}
