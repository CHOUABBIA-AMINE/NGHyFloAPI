package dz.sh.trc.nghyflo.platform;

import dz.sh.trc.nghyflo.platform.security.authorization.PolicyDecision;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PolicyDecisionTest { @Test void denySetsFlag() { assertFalse(PolicyDecision.deny("NO","x").allowed()); } }
