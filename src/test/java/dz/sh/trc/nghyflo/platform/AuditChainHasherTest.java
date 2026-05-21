package dz.sh.trc.nghyflo.platform;

import dz.sh.trc.nghyflo.platform.audit.AuditChainHasher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuditChainHasherTest { @Test void deterministic() { var h=new AuditChainHasher(); assertEquals(h.deterministicHash("a"), h.deterministicHash("a")); } }
