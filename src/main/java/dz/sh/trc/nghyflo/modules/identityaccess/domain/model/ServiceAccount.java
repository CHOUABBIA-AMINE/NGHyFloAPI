/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ServiceAccount
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.domain.model
 *
 * @Description : Represents a non-human principal used by NGHyFlo integrations and edge components.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.domain.model;

import dz.sh.trc.nghyflo.shared.domain.value.UserId;
import java.time.Instant;

public class ServiceAccount {
    private final UserId id;
    private final String name;
    private final Instant createdAt;
    private boolean enabled;

    public ServiceAccount(UserId id, String name, Instant createdAt) {
        if (id == null) {
            throw new IllegalArgumentException("service account id is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("service account name is required");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt is required");
        }
        this.id = id;
        this.name = name.trim();
        this.createdAt = createdAt;
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public UserId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public boolean enabled() {
        return enabled;
    }
}
