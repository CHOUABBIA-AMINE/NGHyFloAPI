/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : Position
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Operational position attached to an organization unit.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.OrganizationUnitId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.PositionCode;
import dz.sh.trc.nghyflo.modules.organization.domain.value.PositionId;

public class Position {
    private final PositionId id;
    private final PositionCode code;
    private final String title;
    private final OrganizationUnitId organizationUnitId;
    private boolean active;

    public Position(PositionId id, PositionCode code, String title, OrganizationUnitId organizationUnitId) {
        if (id == null) {
            throw new IllegalArgumentException("position id is required");
        }
        if (code == null) {
            throw new IllegalArgumentException("position code is required");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("position title is required");
        }
        if (organizationUnitId == null) {
            throw new IllegalArgumentException("organizationUnitId is required");
        }
        this.id = id;
        this.code = code;
        this.title = title.trim();
        this.organizationUnitId = organizationUnitId;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public PositionId id() {
        return id;
    }

    public PositionCode code() {
        return code;
    }

    public String title() {
        return title;
    }

    public OrganizationUnitId organizationUnitId() {
        return organizationUnitId;
    }

    public boolean active() {
        return active;
    }
}
