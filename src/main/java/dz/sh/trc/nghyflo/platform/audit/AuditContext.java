/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditContext
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Runtime audit context joining metadata, payload, and previous hash for chain creation.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

public record AuditContext(
        AuditMetadata metadata,
        String payload,
        String previousHash
) {

    public AuditContext {
        if (metadata == null) {
            throw new IllegalArgumentException("metadata is required");
        }
        if (payload == null || payload.isBlank()) {
            throw new IllegalArgumentException("payload is required");
        }
        previousHash = previousHash == null ? "GENESIS" : previousHash.trim();
    }
}
