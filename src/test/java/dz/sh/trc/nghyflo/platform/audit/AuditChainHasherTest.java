/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditChainHasherTest
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Verifies deterministic audit hash-chain behavior for operational audit records.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

import dz.sh.trc.nghyflo.shared.domain.value.CorrelationId;
import dz.sh.trc.nghyflo.shared.domain.value.TenantId;
import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuditChainHasherTest {

    private final AuditChainHasher hasher = new AuditChainHasher();

    @Test
    void shouldCreateDeterministicPayloadHash() {
        String first = hasher.deterministicHash("payload");
        String second = hasher.deterministicHash("payload");

        assertEquals(first, second);
    }

    @Test
    void shouldChangeHashWhenPreviousHashChanges() {
        AuditContext firstContext = new AuditContext(metadata(), "{\"state\":\"SUBMITTED\"}", "GENESIS");
        AuditContext secondContext = new AuditContext(firstContext.metadata(), firstContext.payload(), "previous-hash");

        assertNotEquals(hasher.chainHash(firstContext), hasher.chainHash(secondContext));
    }

    @Test
    void shouldCreateAuditRecordWithHashChainFields() {
        AuditContext context = new AuditContext(metadata(), "{\"state\":\"APPROVED\"}", "GENESIS");

        AuditRecord record = hasher.createRecord(context);

        assertEquals(context.metadata(), record.metadata());
        assertEquals(context.payload(), record.payload());
        assertEquals("GENESIS", record.previousHash());
        assertNotNull(record.eventHash());
    }

    @Test
    void shouldRejectMissingPayload() {
        assertThrows(IllegalArgumentException.class, () -> hasher.deterministicHash(" "));
        assertThrows(IllegalArgumentException.class, () -> new AuditContext(metadata(), " ", "GENESIS"));
    }

    private AuditMetadata metadata() {
        return new AuditMetadata(
                "audit-1",
                UserId.newId(),
                TenantId.newId(),
                AuditActionType.APPROVE,
                "Measurement",
                "measurement-1",
                CorrelationId.newId(),
                Instant.parse("2026-05-22T12:00:00Z"),
                "APPROVAL_GRANTED"
        );
    }
}
