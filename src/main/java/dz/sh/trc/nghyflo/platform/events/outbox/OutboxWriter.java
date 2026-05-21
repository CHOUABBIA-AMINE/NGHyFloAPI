package dz.sh.trc.nghyflo.platform.events.outbox;

import dz.sh.trc.nghyflo.shared.domain.event.EventMetadata;
import dz.sh.trc.nghyflo.shared.domain.model.DomainEvent;

public interface OutboxWriter {
    void write(DomainEvent event, EventMetadata metadata);
}
