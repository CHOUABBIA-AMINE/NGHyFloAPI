/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditRecord
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Immutable audit evidence record with hash-chain linkage.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

public record AuditRecord(
        AuditMetadata metadata,
        String payload,
        String previousHash,
        String eventHash
) {

    public AuditRecord {
        if (metadata == null) {
            throw new IllegalArgumentException("metadata is required");
        }
        if (payload == null || payload.isBlank()) {
            throw new IllegalArgumentException("payload is required");
        }
        if (previousHash == null || previousHash.isBlank()) {
            throw new IllegalArgumentException("previousHash is required");
        }
        if (eventHash == null || eventHash.isBlank()) {
            throw new IllegalArgumentException("eventHash is required");
        }
    }
}
