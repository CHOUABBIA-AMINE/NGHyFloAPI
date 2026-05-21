package dz.sh.trc.nghyflo.platform.audit;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AuditChainHasher {
    public String deterministicHash(String payload) {
        try {
            MessageDigest d = MessageDigest.getInstance("SHA-256");
            byte[] digest = d.digest(payload.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
