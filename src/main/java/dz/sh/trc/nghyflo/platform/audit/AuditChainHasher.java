/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditChainHasher
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Service
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Computes deterministic hash-chain evidence for immutable operational audit records.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AuditChainHasher {

    public String deterministicHash(String payload) {
        return sha256(requireText(payload, "payload"));
    }

    public String chainHash(AuditContext context) {
        if (context == null) {
            throw new IllegalArgumentException("context is required");
        }
        AuditMetadata metadata = context.metadata();
        String canonicalPayload = String.join("|",
                metadata.auditId(),
                metadata.actorId().value(),
                metadata.tenantId().value(),
                metadata.actionType().name(),
                metadata.targetType(),
                metadata.targetId(),
                metadata.correlationId().value(),
                metadata.occurredAt().toString(),
                metadata.reasonCode(),
                context.previousHash(),
                context.payload()
        );
        return sha256(canonicalPayload);
    }

    public AuditRecord createRecord(AuditContext context) {
        String hash = chainHash(context);
        return new AuditRecord(context.metadata(), context.payload(), context.previousHash(), hash);
    }

    private String sha256(String payload) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(payload.getBytes(StandardCharsets.UTF_8));
            StringBuilder hash = new StringBuilder();
            for (byte currentByte : hashedBytes) {
                hash.append(String.format("%02x", currentByte));
            }
            return hash.toString();
        } catch (Exception exception) {
            throw new IllegalStateException(exception);
        }
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return value;
    }
}
