package dz.sh.trc.nghyflo.platform;

import dz.sh.trc.nghyflo.platform.events.outbox.OutboxEvent;
import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OutboxEventTest { @Test void construct() { assertEquals("agg", new OutboxEvent("1","type","agg","ev","{}","NEW",0,Instant.now(),null,CorrelationId.newId()).aggregateId()); } }
