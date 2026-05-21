/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditChainHasher
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Service
 * @Layer       : Platform
 * @Package     : dz.sh.trc.nghyflo.platform.audit
 *
 * @Description : Computes deterministic hashes for immutable operational audit chain evidence.
 *
 */
package dz.sh.trc.nghyflo.platform.audit;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AuditChainHasher {

    public String deterministicHash(String payload) {
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
}
